@file:JvmName("AudioManager")

package org.frice.utils.media

import org.frice.utils.cast
import java.io.File
import javax.sound.sampled.*

/**
 * Created by ice1000 on 2016/8/16.
 * @author ice1000
 * @since v0.3.1
 */
@JvmOverloads
fun play(file: File, infinite: Boolean = false) = getPlayer(file, infinite).start()

/**
 * @author ice1000
 * @since v0.3.1
 */
@JvmOverloads
fun play(path: String, infinite: Boolean = false) = getPlayer(path, infinite).start()

/**
 * @author ice1000
 * @since v0.3.1
 */
@JvmOverloads
fun getPlayer(file: File, infinite: Boolean = false) = if (infinite) LoopAudioPlayer(file) else OnceAudioPlayer(file)

/**
 * @author ice1000
 * @since v0.3.1
 */
@JvmOverloads
fun getPlayer(path: String, infinite: Boolean = false) = getPlayer(File(path), infinite)

internal const val `{-# BUFFER_SIZE #-}` = 1024

internal fun `{-# getLine #-}`(audioFormat: AudioFormat): SourceDataLine =
	cast(AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, audioFormat)))
