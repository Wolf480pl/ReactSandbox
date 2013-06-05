/*
 * This file is part of ReactSandbox.
 *
 * Copyright (c) 2013 Spout LLC <http://www.spout.org/>
 * ReactSandbox is licensed under the Spout License Version 1.
 *
 * ReactSandbox is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * ReactSandbox is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.reactsandbox;

import org.spout.physics.math.Matrix3x3;
import org.spout.physics.math.Matrix4x4;
import org.spout.physics.math.Quaternion;
import org.spout.physics.math.Vector3;
import org.spout.physics.math.Vector4;

public class MathHelper {
	public static Matrix4x4 asRotationMatrix(Quaternion q) {
		final Matrix3x3 m3 = q.getMatrix();
		return new Matrix4x4(
				m3.get(0, 0), m3.get(0, 1), m3.get(0, 2), 0,
				m3.get(1, 0), m3.get(1, 1), m3.get(1, 2), 0,
				m3.get(2, 0), m3.get(2, 1), m3.get(2, 2), 0,
				0, 0, 0, 1);
	}

	public static Matrix4x4 asTranslationMatrix(Vector3 v) {
		return new Matrix4x4(
				0, 0, 0, v.getX(),
				0, 0, 0, v.getY(),
				0, 0, 0, v.getZ(),
				0, 0, 0, 0);
	}

	public static float[] asArray(Matrix4x4 m) {
		final float[] a = new float[16];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				a[i + j * 4] = m.get(i, j);
			}
		}
		return a;
	}

	public static Vector3 transform(Matrix4x4 m, Vector3 v) {
		final Vector4 v4 = new Vector4(v.getX(), v.getY(), v.getZ(), 1);
		final Vector4 tv4 = Matrix4x4.multiply(m, v4);
		return new Vector3(tv4.getX(), tv4.getY(), tv4.getZ());
	}

	public static Quaternion angleAxisToQuaternion(float angle, float x, float y, float z) {
		final float halfAngle = angle / 2;
		final float q = (float) (Math.sin(halfAngle) / Math.sqrt(x * x + y * y + z * z));
		return new Quaternion(x * q, y * q, z * q, (float) Math.cos(halfAngle));
	}
}