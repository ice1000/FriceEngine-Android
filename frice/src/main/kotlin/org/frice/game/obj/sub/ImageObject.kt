package org.frice.game.obj.sub

import android.graphics.Bitmap
import org.frice.android.obj.CollideBox
import org.frice.android.obj.FObject
import org.frice.android.resource.image.ImageResource
import org.frice.android.utils.graphics.shape.FRectangle

/**
 * Base GameObject class
 *
 * Created by ice1000 on 2016/8/13.
 * @author ice1000
 * @since v0.1
 */
open class ImageObject(var res: ImageResource, override var id: Int,
                       override var x: Double, override var y: Double) : FObject(), FObject.ImageOwner {
	constructor(res: ImageResource, id: Int) : this(res, id, 0.0, 0.0)

	constructor(res: ImageResource) : this(res, -1, 0.0, 0.0)

	constructor(res: ImageResource, x: Double, y: Double) : this(res, -1, x, y)

	constructor(res: Bitmap, x: Double, y: Double) : this(ImageResource.create(res), -1, x, y)

	override fun getResource() = res

	override fun isCollide(other: CollideBox): Boolean = when (other) {
		is ShapeObject -> when (other.collideBox) {
			is FRectangle -> this rectCollideRect other
			// TODO
			else -> this rectCollideRect other
		}
		is ImageObject -> this rectCollideRect other
		else -> false
	}

	override val width: Double
		get() = res.bitmap.width.toDouble()
	override val height: Double
		get() = res.bitmap.height.toDouble()

	override val collideBox = FRectangle(res.bitmap.width, res.bitmap.height)
	override var died = false

	override fun scale(x: Double, y: Double) {
//		res.bitmap = res.bitmap.getScaledInstance((res.bitmap.width * x / 1000.0).toInt(),
//				(res.bitmap.height * y / 1000.0).toInt(), Image.SCALE_DEFAULT) as BufferedImage
		TODO()
//		TODO()
	}

	override val image: Bitmap
		get() = res.bitmap

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || other !is ImageObject) return false
		if ((id != -1 && id == other.id) ||
				res == other.res && x == other.x && y == other.y) return true
		return false
	}

	override fun hashCode(): Int {
		var result = res.hashCode()
		result = 31 * result + id
		result = 31 * result + x.hashCode()
		result = 31 * result + y.hashCode()
		result = 31 * result + anims.hashCode()
		return result
	}
}
