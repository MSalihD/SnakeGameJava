import java.util.Scanner;
public class snake{
	public static int[][] board = new int[10][10];
	public static int random = 196;
	public static int food_x = 0;
	public static int food_y = 0;
	public static int length = 2;
	public static int direction = 1;
	public static int x = 2;
	public static int y = 5;
	public static boolean game_over = false;
	public static char read_one_char(){
		Scanner scan = new Scanner(System.in);
		char res = scan.next().charAt(0);
		if(res == '\n'){
			while(scan.next().charAt(0) != '\n');
		}
		return res;
	}
	public static void print_board(){
		System.out.println("############");
		for(int j = 0;j < 10;j++){
			System.out.print("#");
			for(int i = 0;i < 10;i++){
				if(board[i][j] != 0){
					if(i == x && j == y){
						System.out.print("@");
					}
					else{
						System.out.print("O");
					}
					board[i][j]--;
				}
				else{
					if(i == food_x && j == food_y){
						System.out.print("&");
					}
					else{
						System.out.print(" ");
					}
				}
			}
			System.out.println("#");
		}
		random += x + y + direction + length + 1 + food_x + food_y;
		System.out.println("############");
	}
	public static void handle_input(){
		char input = read_one_char();
		switch(input){
			case 'W':
			case 'w':
			if(direction == 2){
				game_over = true;
			}
			direction = 0;
			break;
			case 'D':
			case 'd':
			if(direction == 3){
				game_over = true;
			}
			direction = 1;
			break; 
			case 'S':
			case 's':
			if(direction == 0){
				game_over = true;
			}
			direction = 2;
			break;
			case 'A':
			case 'a':
			if(direction == 1){
				game_over = true;
			}
			direction = 3;
			break;
		}
	}
	public static void collision_check(){
		try{
			if(direction == 0){
				if(y-- == 0){
					game_over = true;
				}
			}
			else if(direction == 1){ 
				if(x++ == 9){
					game_over = true;
				}
			}
			else if(direction == 2){
				if(y++ == 9){
					game_over = true;
				}	
			}
			else if(direction == 3){
				if(x-- == 0){
					game_over = true;
				}
			}
			if(board[x][y] != 0){
				game_over = true;
			}
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e){
			game_over = true;
		}
	}
	public static boolean food_check(){
		boolean game_playing = true;
		if(x == food_x){
			if(y == food_y){
				food_x = random % 10;
				food_y = (int)(random / 15) % 10;
				if(++length != 100){
					for(int j = 0;j < 10;j++){
						for(int i = 0;i < 10;i++){
							if(board[i][j] != 0){
								board[i][j]++;
							}
						}
					}
				}
				else{
					game_playing = false;
				}
			}
		}
		return game_playing;
	}
	public static void main(String[] args){
		board[1][5] = 1;
		board[2][5] = 2;
		food_x = (random) % 10;
		food_y = (random / 0xF) % 10;
		boolean game_playing = true;
		while(game_playing){
			print_board();
			handle_input();
			collision_check();
			try{board[x][y] = length;}
			catch(java.lang.ArrayIndexOutOfBoundsException e){game_over = true;}
			game_playing = food_check();
			if(game_over){
				game_playing = false;
			}
		}
		if(game_over){
			System.out.println("GAME OVER");
		}
		else{
			System.out.println("YOU WIN");
		}
		System.out.printf("SCORE: %d\n",length);
	}
}
