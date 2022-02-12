package game_files.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 32, height = 32; //	Sprite size
	public static BufferedImage player, enemy;
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Test.PNG"));
		
		player = sheet.crop(width*2, 0, width, height);
		
	}
}
