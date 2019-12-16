package y2019;

import lombok.Data;

@Data
public class Point {
	final int x;
	final int y;

	public Point apply(Direction d){
		return new Point(x + d.xDelta, y+d.yDelta);
	}
}