package y2019;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class P12 {
	public static void main(String[] args) {
		Moon[] moons = new Moon[]{
				new Moon(5, -1, 5),
				new Moon(0, -14, 2),
				new Moon(16, 4, 0),
				new Moon(18, 1, 16)
//				new Moon(-1, 0, 2),
//				new Moon(2, -10, -7),
//				new Moon(4, -8, 8),
//				new Moon(3, 5, -1)
		};
		for (int i = 1; i <= 1000; i++) {
			List<Tuple> gravity = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				int x = 0;
				int y = 0;
				int z = 0;
				for (int k = 0; k < 4; k++) {
					if (j == k) {
						continue;
					}
					Tuple thisMoon = moons[j].position;
					Tuple otherMoon = moons[k].position;
					if (thisMoon.x < otherMoon.x) {
						x++;
					} else if (thisMoon.x > otherMoon.x) {
						x--;
					}
					if (thisMoon.y < otherMoon.y) {
						y++;
					} else if (thisMoon.y > otherMoon.y) {
						y--;
					}
					if (thisMoon.z < otherMoon.z) {
						z++;
					} else if (thisMoon.z > otherMoon.z) {
						z--;
					}
				}
				gravity.add(new Tuple(x, y, z));
			}
			for (int j = 0; j < 4; j++) {
				moons[j].gravity(gravity.get(j));
			}
			for (int j = 0; j < 4; j++) {
				moons[j].applyVelocity();
			}
		}
		int sum = 0;
		for (Moon m : moons) {
			int pos = Math.abs(m.position.x) + Math.abs(m.position.y) + Math.abs(m.position.z);
			int kin = Math.abs(m.velocity.x) + Math.abs(m.velocity.y) + Math.abs(m.velocity.z);
			sum += pos * kin;
		}
		System.out.println(sum);
	}

	@Data
	@AllArgsConstructor
	public static class Moon {
		Tuple position;
		Tuple velocity = new Tuple(0, 0, 0);

		public Moon(int x, int y, int z) {
			this.position = new Tuple(x, y, z);
		}

		public void gravity(Tuple delta) {
			this.velocity.x += delta.x;
			this.velocity.y += delta.y;
			this.velocity.z += delta.z;
		}

		public void applyVelocity() {
			this.position.x += velocity.x;
			this.position.y += velocity.y;
			this.position.z += velocity.z;
		}
	}


	@Data
	@AllArgsConstructor
	public static class Tuple {
		int x;
		int y;
		int z;
	}
}