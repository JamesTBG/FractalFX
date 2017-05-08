package me.oskarmendel.fractals;

import javafx.scene.canvas.GraphicsContext;
import me.oskarmendel.util.Point;

/**
 * The Sierpinski triangle is a fractal constructed from recursively subdivide a
 * triangle into smaller triangles.
 * 
 * More information is available at:
 * https://en.wikipedia.org/wiki/Sierpinski_triangle
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SierpinskiTriangle.java
 */
public class SierpinskiTriangle {

	private static final int MAX_ITER = 8;

	/**
	 * 
	 * @param width
	 * @param height
	 * @param gc
	 */
	public void drawTriangle(int width, int height, GraphicsContext gc) {

		Point p1 = new Point((width / 4), height / 2);
		Point p2 = new Point(width / 2, height);
		Point p3 = new Point((width / 2) + width / 4, height / 2);

		genTriangle(gc, p1, p2, p3, 0);
	}

	/**
	 * 
	 * @param gc
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param iter
	 */
	public void genTriangle(GraphicsContext gc, Point p1, Point p2, Point p3, int iter) {

		if (iter >= MAX_ITER) {
			return;
		}

		double[] xpoint = { p1.getX(), p2.getX(), p3.getX() };
		double[] ypoint = { p1.getY(), p2.getY(), p3.getY() };
		gc.fillPolygon(xpoint, ypoint, 3);

		//Set points for the bottom left triangle.
		Point firstTrianglep3 = Point.midPoint(p1, p2);
		Point firstTrianglep2 = new Point(firstTrianglep3.getX() + (p2.getX() - p3.getX()) / 2,
				firstTrianglep3.getY() + (p2.getY() - p3.getY()) / 2);
		Point firstTrianglep1 = new Point(firstTrianglep3.getX() + (p1.getX() - p3.getX()) / 2,
				firstTrianglep3.getY() + (p1.getY() - p3.getY()) / 2);

		genTriangle(gc, firstTrianglep1, firstTrianglep2, firstTrianglep3, (iter + 1));

		//Set the points for the bottom right triangle.
		Point secondTrianglep3 = Point.midPoint(p2, p3);
		Point secondTrianglep2 = new Point(secondTrianglep3.getX() + (p2.getX() - p1.getX()) / 2,
				secondTrianglep3.getY() + (p2.getY() - p1.getY()) / 2);
		Point secondTrianglep1 = new Point(secondTrianglep3.getX() + (p3.getX() - p1.getX()) / 2,
				secondTrianglep3.getY() + (p3.getY() - p1.getY()) / 2);

		genTriangle(gc, secondTrianglep1, secondTrianglep2, secondTrianglep3, (iter + 1));
		
		//Set the points for the top triangle.
		Point thirdTrianglep3 = Point.midPoint(p1, p3);
		Point thirdTrianglep2 = new Point(thirdTrianglep3.getX() + (p1.getX() - p2.getX()) / 2,
				thirdTrianglep3.getY() + (p1.getY() - p2.getY()) / 2);
		Point thirdTrianglep1 = new Point(thirdTrianglep3.getX() + (p3.getX() - p2.getX()) / 2,
				thirdTrianglep3.getY() + (p3.getY() - p2.getY()) / 2);

		genTriangle(gc, thirdTrianglep1, thirdTrianglep2, thirdTrianglep3, (iter + 1));
	}
}
