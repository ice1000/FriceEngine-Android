package org.frice.resource.image

import org.frice.platform.FriceImage
import org.frice.platform.adapter.JfxImage
import org.frice.platform.adapter.DroidImage
import org.frice.resource.FResource

/**
 * Created by ice1000 on 2016/8/13.
 * @author ice1000
 * @since v0.1
 */
abstract class ImageResource : FResource {

	companion object Factories {
		@JvmStatic
		fun create(image: FriceImage): ImageResource = ImageResourceImpl(image)

		operator fun invoke(image: FriceImage): ImageResource = create(image)

		@JvmStatic
		fun fromImage(image: FriceImage): ImageResource = create(image)

		@JvmStatic
		fun fromPath(path: String) = FileImageResource(path)

		@JvmStatic
		fun fromWeb(url: String) = WebImageResource(url)

		@JvmStatic
		fun empty() = create(DroidImage(1, 1))

		@JvmStatic
		fun emptyFX() = create(JfxImage(1, 1))
	}

	abstract var image: FriceImage
	override val resource get() = image

	/**
	 * @param x size proportion on x axis
	 * @param y size proportion on y axis
	 * @return scaled image
	 */
	fun scaled(x: Double, y: Double) = fromImage(image.scale(x, y))

	/**
	 * @param x location x
	 * @param y location y
	 * @param width width
	 * @param height height
	 * @return a sub image
	 */
	fun part(x: Int, y: Int, width: Int, height: Int) = fromImage(image.part(x, y, width, height))

	/**
	 * @since v1.7.11
	 * @param orientation true: horizontal; false: vertical
	 * @return flipped image
	 */
	fun flip(orientation: Boolean) = fromImage(image.flip(orientation))
}