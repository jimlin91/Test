package FerrisWeel;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected.Half;
import org.bukkit.block.data.type.Fence;
import org.bukkit.block.data.type.Gate;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.Stairs.Shape;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Entity;

import myPack.Main;

public class FerrisWeelModel {
	
	
	public static final int[][] getFerrisCenter(int color){
		int[][] ferrisCenter = new int[Main.sizeI][Main.sizeJ];
		for(int i =0;i<ferrisCenter.length;i++) {
			for(int j =0;j<ferrisCenter[0].length;j++) {
				if(i<(ferrisCenter.length-1)/2 && i>5 &&j==(ferrisCenter[0].length-1)/2) {
					ferrisCenter[i][j] =color;
				}
				else if(j==(ferrisCenter[0].length-1)/2 && i>=2 &&i<=5) {
					ferrisCenter[i][j] = 1;
				}
				else {
					ferrisCenter[i][j] = 0;
				}
			}
		}
		
		return ferrisCenter;
	}
	
	public static final int[][] getOutSideFrame(){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		frame[8][12] =1;
		frame[9][11] =1;
		for(int i =0;i<frame.length;i++) {
			for(int j =0;j<frame[0].length;j++) {
				if(j<=22 && j>17 && i==5) {
					frame[i][j] =1;
				}
				if(j<=17 && j>14 && i==6) {
					frame[i][j] =1;
				}
				if(j<=14 && j>12 && i==7) {
					frame[i][j] =1;
				}
			}
		}
		
		
		frame = mirror(frame,90);
		frame = mirror(frame,180);
		frame = mirror(frame,45);
		
		
		
		return frame;
	}
	
	public static final int[][] getMiddleFrame(){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		frame[12][22] = 10;
		frame[12][21] = 10;
		frame[12][20] = 10;
		
		frame[13][19] = 10;
		frame[13][18] = 10;
		
		frame[14][17] = 10;
		frame[15][16] = 10;
		
		
		frame = mirror(frame,90);
		frame = mirror(frame,180);
		frame = mirror(frame,45);
		
		return frame;
	}
	
	public static final int[][] getGlowstone(){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		
		frame[12][22] = 20;

		frame[4][22] = 20;
		
		frame = mirror(frame,180);
		frame = mirror(frame,45);
			
		return frame;
	}
	
	public static int[][] getTrapDoor(int[][] glowStone){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		
		for(int i =0;i<frame.length;i++) {
			for(int j =0;j<frame[0].length;j++) {
				if(glowStone[i][j] == 20) {
					frame[i][j+1] = 30;
					frame[i][j-1] = 31;
					frame[i+1][j] = 32;
					frame[i-1][j] = 33;
				}
			}
		}
		
		return frame;
	}
	
	public static int[][] getFrontTrapDoor(int[][] glowStone){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		
		for(int i =0;i<frame.length;i++) {
			for(int j =0;j<frame[0].length;j++) {
				if(glowStone[i][j] == 20) {
					frame[i][j] = 34;
				}
			}
		}
		
		return frame;
	}
	
	public static int[][] getBackTrapDoor(int[][] glowStone){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		
		for(int i =0;i<frame.length;i++) {
			for(int j =0;j<frame[0].length;j++) {
				if(glowStone[i][j] == 20) {
					frame[i][j] = 35;
				}
			}
		}
		
		return frame;
	}
	
	public static int[][] getOutsideCover(){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		
		for(int i = -1;i<=1;i++) {
			frame[(Main.sizeI-1)/2-2][(Main.sizeJ-1)/2+i] = 10;
			frame[(Main.sizeI-1)/2+2][(Main.sizeJ-1)/2+i] = 10;
		}
		for(int i = -2;i<=2;i++) {
			frame[(Main.sizeI-1)/2-1][(Main.sizeJ-1)/2+i] = 10;
			frame[(Main.sizeI-1)/2][(Main.sizeJ-1)/2+i] = 10;
			frame[(Main.sizeI-1)/2+1][(Main.sizeJ-1)/2+i] = 10;
		}
		
		return frame;
	}
	public static int[][] getCenterRod(){
		int[][] frame = new int[Main.sizeI][Main.sizeJ];
		frame[(Main.sizeI-1)/2-1][(Main.sizeJ-1)/2] = 11;
		frame[(Main.sizeI-1)/2][(Main.sizeJ-1)/2-1] = 11;
		frame[(Main.sizeI-1)/2][(Main.sizeJ-1)/2] = 11;
		frame[(Main.sizeI-1)/2][(Main.sizeJ-1)/2+1] = 11;
		frame[(Main.sizeI-1)/2+1][(Main.sizeJ-1)/2] = 11;
		
		return frame;
	}
	
	public static int[][][] getBuckets(double angle){
		int[][][] frame = new int[7][Main.sizeI][Main.sizeJ];
		int[][] anchor = new int[Main.sizeI][Main.sizeJ];
		for(int i =0;i<=7;i++) {
			int[][] buffer = new int[Main.sizeI][Main.sizeJ];
			buffer[2][(Main.sizeJ-1)/2] = i+2;
			anchor = add(anchor,turnAngle(buffer,(double)(i*45)+angle));
		}
		
		for(int i =0;i<frame[0].length;i++) {
			for(int j =0;j<frame[0][0].length;j++) {
				if(anchor[i][j] !=0) {
					int color = anchor[i][j];
					frame[0][i][j] = 55;
					frame[6][i][j] = 55;
					
					frame[1][i-1][j-1] = 80;
					
					for(int top =0;top<=1;top++) {
						frame[top*6][i-2][j-2] = 42;
						frame[top*6][i-2][j+2] = 42;
						
						for(int r = -1; r<=1;r++) {
							frame[top*6][i-2][j+r] = 40;
						}
						
						frame[top*6][i-1][j-3] = 43;
						frame[top*6][i-1][j+3] = 44;
						
						frame[top*6][i-1][j-2] = 41;
						frame[top*6][i-1][j+2] = 41;
					}
					
					// roof
					for(int top=1;top<=5;top++) {
						frame[top][i-2][j-2] = 71;
						frame[top][i-2][j+2] = 71;
						
						for(int r = -1; r<=1;r++) {
							frame[top][i-2][j+r] = 70;
						}
						
						frame[top][i-1][j-3] = 43;
						frame[top][i-1][j+3] = 44;
					}
					//middle of the basket
					for(int top =2;top<=4;top++) {
						
						for(int r =-2;r<=2;r+=4) {
							frame[top][i-1][j+r] = 1;
							frame[top][i+1][j+r] = 1;
							
							frame[top][i][j+r] = 81;
						}
						
						for(int r =-2;r<=2;r++) {
							frame[top][i+2][j+r] = color;
						}
						
						if(top!=2) {
							frame[top][i+1][j-1] = 61;
						}
						frame[top][i+1][j+1] = 60;
						
					}
					
					// add color pillar
					for(int r=-2;r<=2;r+=2) {
						for(int y=-1;y<=1;y++) {
							frame[1][i+y][j+r] = color;
							frame[5][i+y][j+r] = color;
						}
					}
					
					// add pain glass
					for(int y=-1;y<=1;y++) {
						frame[1][i+y][j+1] = 82;
						frame[5][i+y][j+1] = 82;
						frame[5][i+y][j-1] = 82;
					}
					
					//add straight stairs
					for(int r =-1;r<=1;r++) {
						frame[1][i+2][j+r] = 49;
						frame[5][i+2][j+r] = 50;
					}
					
					// add outer stairs
					frame[1][i+2][j-2] =45;
					frame[1][i+2][j+2] =46;
					frame[5][i+2][j-2] =47;
					frame[5][i+2][j+2] =48;
					
					//add gate
					if(i>= Main.sizeI-4) {
						frame[1][i][j-1] = 57;
						frame[1][i+1][j-1] = 57;
					}
					else {
						frame[1][i][j-1] = 56;
						frame[1][i+1][j-1] = 56;
					}
				}
			}
		}
		return frame;
	}
	
	public static void teleportPlayer(double angle,Main plugin) {
		List<Double> pos = plugin.data.getConfig().getDoubleList("Center.pos");
		Location loc = new Location(Bukkit.getWorld(plugin.data.getConfig().getString("Center.world")),pos.get(0),pos.get(1),pos.get(2));
		
		double oneTimeTurn = plugin.data.getConfig().getDouble("Custom.angle");
		
		int[][] anchor = new int[Main.sizeI][Main.sizeJ];
		int[][] preAnchor = new int[Main.sizeI][Main.sizeJ];
		for(int i =0;i<=7;i++) {
			int[][] buffer = new int[Main.sizeI][Main.sizeJ];
			buffer[2][(Main.sizeJ-1)/2] = i+2;
			anchor = add(anchor,turnAngle(buffer,(double)(i*45)+angle));
			preAnchor = add(preAnchor,turnAngle(buffer,(double)(i*45)+angle-oneTimeTurn));
		}
		
		for(int i =0;i<anchor.length;i++) {
			for(int j =0 ;j<anchor[0].length;j++) {
				if(anchor[i][j] !=0 && anchor[i][j] != preAnchor[i][j]) {
					
					if(plugin.data.getConfig().getInt("Center.facing") ==1) {
						loc.setX(pos.get(0)+j-(anchor[0].length-1)/2);
						loc.setY(pos.get(1)+(anchor.length-1)/2-i);
						loc.setZ(pos.get(2)-4);
					}
					else if(plugin.data.getConfig().getInt("Center.facing") ==2) {
						loc.setZ(pos.get(2)+j-(anchor.length-1)/2);
						loc.setY(pos.get(1)+(anchor.length-1)/2-i);
						loc.setX(pos.get(0)+4);
					}
					else if(plugin.data.getConfig().getInt("Center.facing") ==3) {
						loc.setX(pos.get(0)+(anchor[0].length-1)/2-j);
						loc.setY(pos.get(1)+(anchor.length-1)/2-i);
						loc.setZ(pos.get(2)+4);
					}
					else if(plugin.data.getConfig().getInt("Center.facing") ==4) {
						loc.setZ(pos.get(2)+(anchor.length-1)/2-j);
						loc.setY(pos.get(1)+(anchor.length-1)/2-i);
						loc.setX(pos.get(0)-4);
					}
					double dx,dy;
					dx =0;
					dy =0;
					
					out:for(int pi =0;pi<anchor.length;pi++) {
						for(int pj =0;pj<anchor[0].length;pj++) {
							if(preAnchor[pi][pj] == anchor[i][j]) {
								dx = j-pj;
								dy = -(i - pi);
								break out;
							}
						}
					}
					
					for(Entity entity :loc.getWorld().getNearbyEntities(loc,2,2,2)) {
						Location entityLocation = entity.getLocation();
						
						if(plugin.data.getConfig().getInt("Center.facing") ==1) {
							entityLocation.setX(entityLocation.getX()+dx);
							entityLocation.setY(entityLocation.getY()+dy);
						}
						else if(plugin.data.getConfig().getInt("Center.facing") ==2) {
							entityLocation.setZ(entityLocation.getZ()+dx);
							entityLocation.setY(entityLocation.getY()+dy);
						}
						else if(plugin.data.getConfig().getInt("Center.facing") ==3) {
							entityLocation.setX(entityLocation.getX()-dx);
							entityLocation.setY(entityLocation.getY()+dy);
						}
						else if(plugin.data.getConfig().getInt("Center.facing") ==4) {
							entityLocation.setZ(entityLocation.getZ()-dx);
							entityLocation.setY(entityLocation.getY()+dy);
						}
						entity.teleport(entityLocation);
						
					}
					
				}
			}
		}
	}
	
	 static int[][] mirror(int[][] mat,double angle){
		int[][] result = mat.clone();
		for(int i = 0;i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				if(result[i][j] !=0) {
					double x = j;
					double y = i;
					double sin = Math.sin(Math.toRadians(angle*2));
					double cos = Math.cos(Math.toRadians(angle*2));
					int newX = (int) Math.round((x-(mat[0].length-1)/2)*cos+(y-(mat.length-1)/2)*sin)+(mat[0].length-1)/2;
					int newY = (int) Math.round((x-(mat[0].length-1)/2)*sin+(y-(mat.length-1)/2)*-cos)+(mat.length-1)/2;
					result[newY][newX] =result[i][j];
				}
			}
		}
		return result;
	}
	
	 public static int[][] turnAngle(int[][] mat,double angle){
			int[][] result = new int[Main.sizeI][Main.sizeJ];
			for(int i = 0;i<mat.length;i++) {
				for(int j=0;j<mat[0].length;j++) {
					if(mat[i][j] !=0) {
						double x = j;
						double y = i;
						double sin = Math.sin(Math.toRadians(angle));
						double cos = Math.cos(Math.toRadians(angle));
						int newX = (int) Math.round((x-(result[0].length-1)/2)*cos+(y-(result.length-1)/2)*-sin)+(result[0].length-1)/2;
						int newY = (int) Math.round((x-(result[0].length-1)/2)*sin+(y-(result.length-1)/2)*cos)+(result.length-1)/2;
						result[newY][newX] =mat[i][j];
					}
				}
			}
			return result;
		}
		public static int[][] add(int[][] mat1,int[][] mat2) {
			int[][] result = mat1;
			for(int i = 0;i<mat1.length;i++) {
				for(int j=0;j<mat1[0].length;j++) {
					if(mat2[i][j] != 0) {
						result[i][j] = mat2[i][j];
					}
				}
			}
			
			return result;
		}
	 
	 public static void showFerrisWeel(int[][] frame,Main plugin,int layer) {
		 List<Double> pos = plugin.data.getConfig().getDoubleList("Center.pos");
		 Location loc = new Location(Bukkit.getWorld(plugin.data.getConfig().getString("Center.world")),pos.get(0),pos.get(1),pos.get(2));
		 
			for(int i =0;i<frame.length;i++) {
				for(int j =0;j<frame[0].length;j++) {
					if(plugin.data.getConfig().getInt("Center.facing") ==1) {
						loc.setX(pos.get(0)+j-(frame[0].length-1)/2);
						loc.setY(pos.get(1)+(frame.length-1)/2-i);
						loc.setZ(pos.get(2)-layer);
					}
					else if(plugin.data.getConfig().getInt("Center.facing") ==2) {
						loc.setZ(pos.get(2)+j-(frame.length-1)/2);
						loc.setY(pos.get(1)+(frame.length-1)/2-i);
						loc.setX(pos.get(0)+layer);
					}
					else if(plugin.data.getConfig().getInt("Center.facing") ==3) {
						loc.setX(pos.get(0)+(frame[0].length-1)/2-j);
						loc.setY(pos.get(1)+(frame.length-1)/2-i);
						loc.setZ(pos.get(2)+layer);
					}
					else if(plugin.data.getConfig().getInt("Center.facing") ==4) {
						loc.setZ(pos.get(2)+(frame.length-1)/2-j);
						loc.setY(pos.get(1)+(frame.length-1)/2-i);
						loc.setX(pos.get(0)-layer);
					}
					
					if(frame[i][j] ==1) {
						loc.getBlock().setType(Material.WHITE_CONCRETE);
					}
					else if(frame[i][j] ==2) {
						loc.getBlock().setType(Material.PURPLE_CONCRETE);
					}
					else if(frame[i][j] ==3) {
						loc.getBlock().setType(Material.BROWN_CONCRETE);
					}
					else if(frame[i][j] ==4) {
						loc.getBlock().setType(Material.ORANGE_CONCRETE);
					}
					else if(frame[i][j] ==5) {
						loc.getBlock().setType(Material.MAGENTA_CONCRETE);
					}
					else if(frame[i][j] ==6) {
						loc.getBlock().setType(Material.GRAY_CONCRETE);
					}
					else if(frame[i][j] ==7) {
						loc.getBlock().setType(Material.LIME_CONCRETE);
					}
					else if(frame[i][j] ==8) {
						loc.getBlock().setType(Material.YELLOW_CONCRETE);
					}
					else if(frame[i][j] ==9) {
						loc.getBlock().setType(Material.LIGHT_BLUE_CONCRETE);
					}
					else if(frame[i][j] ==10) {
						loc.getBlock().setType(Material.BLACK_CONCRETE);
					}
					else if(frame[i][j] ==11) {
						loc.getBlock().setType(Material.RED_CONCRETE);
					}
					else if(frame[i][j] ==20) {
						loc.getBlock().setType(Material.GLOWSTONE);
					}
					else if(frame[i][j] ==30) {
						int direction = plugin.data.getConfig().getInt("Center.facing") ;
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_TRAPDOOR);

						TrapDoor trapDoor = (TrapDoor)block.getBlockData();
						
						trapDoor.setOpen(true);
						if(direction ==1) {
							trapDoor.setFacing(BlockFace.EAST);						
						}
						else if(direction ==2) {
							trapDoor.setFacing(BlockFace.SOUTH);
						}
						else if(direction ==3) {
							trapDoor.setFacing(BlockFace.WEST);
						}
						else if(direction ==4) {
							trapDoor.setFacing(BlockFace.NORTH);
						}
						
						block.setBlockData(trapDoor);
					}
					else if(frame[i][j] ==31) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_TRAPDOOR);

						TrapDoor trapDoor = (TrapDoor)block.getBlockData();
						
						trapDoor.setOpen(true);
						
						if(direction ==1) {
							trapDoor.setFacing(BlockFace.WEST);						
						}
						else if(direction ==2) {
							trapDoor.setFacing(BlockFace.NORTH);
						}
						else if(direction ==3) {
							trapDoor.setFacing(BlockFace.EAST);
						}
						else if(direction ==4) {
							trapDoor.setFacing(BlockFace.SOUTH);
						}
						
						block.setBlockData(trapDoor);
					}
					else if(frame[i][j] ==32) {
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_TRAPDOOR);

						TrapDoor trapDoor = (TrapDoor)block.getBlockData();
						
						trapDoor.setOpen(false);
						trapDoor.setHalf(Half.TOP);
						
						block.setBlockData(trapDoor);
					}
					else if(frame[i][j] ==33) {
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_TRAPDOOR);
						
						TrapDoor trapDoor = (TrapDoor)block.getBlockData();
						
						trapDoor.setOpen(false);
						trapDoor.setHalf(Half.BOTTOM);
						
						block.setBlockData(trapDoor);
					}
					else if(frame[i][j] ==34) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_TRAPDOOR);
						
						TrapDoor trapDoor = (TrapDoor)block.getBlockData();
						
						trapDoor.setOpen(true);
						
						if(direction ==1) {
							trapDoor.setFacing(BlockFace.SOUTH);						
						}
						else if(direction ==2) {
							trapDoor.setFacing(BlockFace.WEST);
						}
						else if(direction ==3) {
							trapDoor.setFacing(BlockFace.NORTH);
						}
						else if(direction ==4) {
							trapDoor.setFacing(BlockFace.EAST);
						}
						
						block.setBlockData(trapDoor);
					}
					else if(frame[i][j] ==35) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_TRAPDOOR);
						
						TrapDoor trapDoor = (TrapDoor)block.getBlockData();
						
						trapDoor.setOpen(true);
						
						if(direction ==1) {
							trapDoor.setFacing(BlockFace.NORTH);						
						}
						else if(direction ==2) {
							trapDoor.setFacing(BlockFace.EAST);
						}
						else if(direction ==3) {
							trapDoor.setFacing(BlockFace.SOUTH);
						}
						else if(direction ==4) {
							trapDoor.setFacing(BlockFace.WEST);
						}
						
						block.setBlockData(trapDoor);
					}
					else if(frame[i][j] ==40) {
						loc.getBlock().setType(Material.QUARTZ_BLOCK);
					}
					else if(frame[i][j] ==41) {
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_SLAB);
						Slab slab = (Slab)block.getBlockData();
						slab.setType(Slab.Type.TOP);
						block.setBlockData(slab);
					}
					else if(frame[i][j] ==42) {
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_SLAB);
						Slab slab = (Slab)block.getBlockData();
						slab.setType(Slab.Type.BOTTOM);
						block.setBlockData(slab);
					}
					else if(frame[i][j] ==43) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.BOTTOM);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.EAST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.WEST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.NORTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==44) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.BOTTOM);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.WEST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.NORTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.EAST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==45) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.TOP);
						stairs.setShape(Shape.OUTER_LEFT);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.EAST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.WEST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.NORTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==46) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.TOP);
						stairs.setShape(Shape.OUTER_RIGHT);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.WEST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.NORTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.EAST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==47) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.TOP);
						stairs.setShape(Shape.OUTER_RIGHT);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.EAST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.WEST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.NORTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==48) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.TOP);
						stairs.setShape(Shape.OUTER_LEFT);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.WEST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.NORTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.EAST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==49) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.TOP);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.NORTH);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.EAST);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.WEST);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==50) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.QUARTZ_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.TOP);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.SOUTH);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.WEST);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.NORTH);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.EAST);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==56) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.DARK_OAK_FENCE_GATE);
						
						Gate gate = (Gate)block.getBlockData();
						
						gate.setOpen(false);
						
						if(direction ==1) {
							gate.setFacing(BlockFace.SOUTH);						
						}
						else if(direction ==2) {
							gate.setFacing(BlockFace.WEST);
						}
						else if(direction ==3) {
							gate.setFacing(BlockFace.NORTH);
						}
						else if(direction ==4) {
							gate.setFacing(BlockFace.EAST);
						}
						
						block.setBlockData(gate);
					}
					else if(frame[i][j] ==57) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.DARK_OAK_FENCE_GATE);
						
						Gate gate = (Gate)block.getBlockData();
						
						gate.setOpen(true);
						
						if(direction ==1) {
							gate.setFacing(BlockFace.SOUTH);						
						}
						else if(direction ==2) {
							gate.setFacing(BlockFace.WEST);
						}
						else if(direction ==3) {
							gate.setFacing(BlockFace.NORTH);
						}
						else if(direction ==4) {
							gate.setFacing(BlockFace.EAST);
						}
						
						block.setBlockData(gate);
					}
					else if(frame[i][j] ==55) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.DARK_OAK_FENCE);
						
						Fence fence = (Fence)block.getBlockData();
						
						if(direction ==1 || direction ==3) {
							fence.setFace(BlockFace.NORTH, true);
							fence.setFace(BlockFace.SOUTH, true);
						}
						else if(direction ==2 || direction ==4) {
							fence.setFace(BlockFace.EAST, true);
							fence.setFace(BlockFace.WEST, true);
						}
						
						block.setBlockData(fence);
					}
					else if(frame[i][j] ==60) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.BOTTOM);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.EAST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.WEST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.NORTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==61) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.JUNGLE_STAIRS);
						
						Stairs stairs = (Stairs)block.getBlockData();
						
						stairs.setHalf(Half.BOTTOM);
						
						if(direction ==1) {
							stairs.setFacing(BlockFace.WEST);						
						}
						else if(direction ==2) {
							stairs.setFacing(BlockFace.NORTH);
						}
						else if(direction ==3) {
							stairs.setFacing(BlockFace.EAST);
						}
						else if(direction ==4) {
							stairs.setFacing(BlockFace.SOUTH);
						}
						
						block.setBlockData(stairs);
					}
					else if(frame[i][j] ==70) {
						Block block = loc.getBlock();
						block.setType(Material.DARK_OAK_SLAB);
						Slab slab = (Slab)block.getBlockData();
						slab.setType(Slab.Type.TOP);
						block.setBlockData(slab);
					}
					else if(frame[i][j] ==71) {
						Block block = loc.getBlock();
						block.setType(Material.DARK_OAK_SLAB);
						Slab slab = (Slab)block.getBlockData();
						slab.setType(Slab.Type.BOTTOM);
						block.setBlockData(slab);
					}
					else if(frame[i][j] ==80) {
						loc.getBlock().setType(Material.WHITE_STAINED_GLASS);
					}
					else if(frame[i][j] ==81) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.WHITE_STAINED_GLASS_PANE);
						
						GlassPane glassPane = (GlassPane)block.getBlockData();
						
						if(direction ==1 || direction ==3) {
							glassPane.setFace(BlockFace.NORTH, true);
							glassPane.setFace(BlockFace.SOUTH, true);
						}
						else if(direction ==2 || direction ==4) {
							glassPane.setFace(BlockFace.EAST, true);
							glassPane.setFace(BlockFace.WEST, true);
						}
						
						block.setBlockData(glassPane);
					}
					else if(frame[i][j] ==82) {
						int direction = plugin.data.getConfig().getInt("Center.facing");
						Block block = loc.getBlock();
						block.setType(Material.WHITE_STAINED_GLASS_PANE);
						
						GlassPane glassPane = (GlassPane)block.getBlockData();
						
						if(direction ==1 || direction ==3) {
							glassPane.setFace(BlockFace.EAST, true);
							glassPane.setFace(BlockFace.WEST, true);
						}
						else if(direction ==2 || direction ==4) {
							glassPane.setFace(BlockFace.NORTH, true);
							glassPane.setFace(BlockFace.SOUTH, true);
						}
						
						block.setBlockData(glassPane);
					}
					else {
						loc.getBlock().setType(Material.AIR);
					}
				}
			}
		 
	 }
	
}
