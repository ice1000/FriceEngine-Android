package org.frice.game.obj.sub

import org.frice.game.anim.move.DoublePair
import org.frice.game.obj.CollideBox
import org.frice.game.obj.FObject
import org.frice.game.resource.graphics.ColorResource
import org.frice.game.utils.graphics.shape.FRectangle
import org.frice.game.utils.graphics.shape.FShape

/**
 * an object with a utils and a shape, used to create an simple object quickly
 * instead of load from an image file.
 *
 * Created by ice1000 on 2016/8/14.
 * @author ice1000
 * @since v0.1.1
 */
open class ShapeObject(var res: ColorResource, override val collideBox: FShape, override var id: Int,
                       override var x: Double, override var y: Double) : FObject() {
	constructor(res: ColorResource, shape: FShape, x: Double, y: Double) : this(res, shape, -1, x, y)

	constructor(res: ColorResource, shape: FShape, id: Int) : this(res, shape, id, 0.0, 0.0)

	constructor(res: ColorResource, shape: FShape) : this(res, shape, -1)

	private var scale = DoublePair(1.0, 1.0)

	override var height: Double
		get() = (collideBox.height * scale.y)
		set(value) {
			collideBox.height = (value / scale.y).toInt()
		}

	override var width: Double
		get () = (collideBox.width * scale.x)
		set(value) {
			collideBox.width = (value / scale.x).toInt()
		}

	override var died = false

	override fun isCollide(other: CollideBox): Boolean = when (other) {
		is ShapeObject -> when (other.collideBox) {
			is FRectangle -> when (collideBox) {
				is FRectangle -> this rectCollideRect other
//				is FOval ->
				else -> this rectCollideRect other
			}
//			is FOval ->
			else -> this rectCollideRect other
		}
		is ImageObject -> when (collideBox) {
			is FRectangle -> this rectCollideRect other
			else -> this rectCollideRect other
		}
		else -> false
	}

	override fun getResource() = res

	override fun scale(x: Double, y: Double) {
		scale.x += x
		scale.y += y
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
//		if (other == null || other !is ShapeObject) return false
//		if ((id != -1 && id == other.id) ||
//				res == other.res && x == other.x && y == other.y) return true
		return false
	}

	override fun hashCode(): Int {
		var result = res.hashCode()
		result = 31 * result + id
		return result
	}

}