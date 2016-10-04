package org.frice.game.utils.audio

import org.frice.game.utils.misc.until
import java.io.File
import javax.sound.sampled.*

/**
 * From https://github.com/ice1000/Dekoder
 *
 * Created by ice1000 on 2016/8/16.
 * @author ice1000
 * @since v0.3.1
 */
class AudioPlayer internal constructor(file: File) {
	internal constructor(path: String) : this(File(path))

	internal fun main() = {
		line.open()
		line.start()
		var inBytes = 0
//		var cnt = 0
		val audioData = ByteArray(BUFFER_SIZE)
		until (inBytes == -1 || exited) {
//			FLog.debug("loop ${cnt++}")
			inBytes = audioInputStream.read(audioData, 0, BUFFER_SIZE)
			if (inBytes >= 0) line.write(audioData, 0, inBytes)
		}
		line.drain()
		line.close()
//		FLog.info("Ended playing")
	}

	private val thread = Thread { main() }

	companion object {
		@JvmField val BUFFER_SIZE = 2048

		private fun getLine(audioFormat: AudioFormat): SourceDataLine {
			return (AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java,
					audioFormat)) as SourceDataLine).apply {
				open(audioFormat)
			}
		}
	}

	private var audioInputStream: AudioInputStream
	private var line: SourceDataLine

	private var format: AudioFormat

	init {
		audioInputStream = AudioSystem.getAudioInputStream(file)
		format = audioInputStream.format
		line = getLine(format)
	}

	private var exited = false

	fun exit() {
		exited = true
		thread.join()
	}

	fun start() = thread.start()
}
