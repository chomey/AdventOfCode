package y2019;

import lombok.Data;

@Data
public class Point implements Comparable<Point>{
	final int x;
	final int y;

	public Point apply(Direction d){
		return new Point(x + d.xDelta, y+d.yDelta);
	}

	@Override
	public int compareTo(Point o) {
		if(x != o.x){
			return Integer.compare(x, o.x);
		}
		return Integer.compare(y, o.y);
	}
}