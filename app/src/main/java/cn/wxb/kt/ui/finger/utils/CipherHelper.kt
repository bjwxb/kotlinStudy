package cn.wxb.kt.ui.finger.utils

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.security.Key
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator

/**
 * @desc: 加密类，用于判断指纹的合法性
 * @author: wuxiaobo
 * @date: 2022/7/6 14:49
 */
@RequiresApi(Build.VERSION_CODES.M)
class CipherHelper {

    companion object {
        private const val KEY_NAME = "com.wxb.finger"
        private const val KEYSTORE_NAME = "com.wxb.finger.keystore"

        private const val KEY_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$KEY_ALGORITHM/$BLOCK_MODE/$ENCRYPTION_PADDING"
    }

    private var keystore: KeyStore = KeyStore.getInstance(KEYSTORE_NAME)

    init {
        keystore.load(null)
    }

    /**
     * 获得Cipher
     *
     * @return
     */
    fun createCipher(): Cipher? {
        var cipher: Cipher? = null
        try {
            cipher = createCipher(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cipher
    }

    /**
     * 创建一个Cipher，用于 FingerprintManager.CryptoObject 的初始化
     * https://developer.android.google.cn/reference/javax/crypto/Cipher.html
     *
     * @param retry
     * @return
     * @throws Exception
     */
    private fun createCipher(retry: Boolean): Cipher? {
        val key = getKey()
        val cipher =
            Cipher.getInstance(TRANSFORMATION) // Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
        try {
            cipher.init(Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE, key)
        } catch (e: KeyPermanentlyInvalidatedException) {
            keystore.deleteEntry(KEY_NAME)
            if (retry) {
                createCipher(false)
            }
            throw Exception("Could not create the cipher for fingerprint authentication.", e)
        }
        return cipher
    }

    private fun getKey(): Key {
        if (!keystore.isKeyEntry(KEY_NAME)) {
            createKey()
        }
        return keystore.getKey(KEY_NAME, null)
    }

    private fun createKey() {
        val keyGen: KeyGenerator = KeyGenerator.getInstance(
            KEY_ALGORITHM,
            KEYSTORE_NAME
        )
        val keyGenSpec = KeyGenParameterSpec.Builder(
            KEY_NAME,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(BLOCK_MODE)
            .setEncryptionPaddings(ENCRYPTION_PADDING)
            .setUserAuthenticationRequired(true)
            .build()
        keyGen.init(keyGenSpec)
        keyGen.generateKey()
    }
}