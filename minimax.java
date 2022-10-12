//Βασίλης Βογιάννου 3193
//Δήμητρα Μαχαιρίδου 4108
//Κωσταντίνος Γκέλιας 2669


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class State{
	
	//The player currently playing.
	boolean hasChildren = false;
	private char player;
	private int[] currentPosA = {0,0};
	private int[] currentPosB = {0,0};
	private int[] oldPosA = {0,0};
	private int[] oldPosB = {0,0};
	private State parent;
	private State [] children ;
	private static int M ;
	private static int N ;
	private char [][] board;
	
	
	//"B" = Black and "W" = White
	public State(int M,int N,char[][] oldBoard){
		this.M = M ;
		this.N = N ;
		this.board = new char[M][N];
		for (int i = 0; i<M ; i++){
			for (int j = 0 ; j < N ; j++){
				this.board[i][j] = oldBoard[i][j];
			}
		}
		this.children = new State[8];
	}


	//Get & Set
	char[][] get_board(){
		return this.board ;
	}

	char get_player(){
		return this.player;
	}
	
	void set_player(char player){
		this.player = player;
	}
	int [] get_pos_player(){
		int[] temp2 = new int[2];
		if (this.player == 'A'){
			for (int i = 0; i<M ; i++){
				for (int j = 0 ; j < N ; j++){
					if (board[i][j] == 'A'){
						this.currentPosA[0]= i ;
						this.currentPosA[1]= j ;
						temp2[0]=i;
						temp2[1]=j;
					}
				}	
			}
			
		}
		else{
			for (int i = 0; i<M ; i++){
				for (int j = 0 ; j < N ; j++){
					if (board[i][j] == 'X'){
						this.currentPosB[0]= i ;
						this.currentPosB[1]= j ;
						temp2[0]=i;
						temp2[1]=j;

					}
				}
			}
		}
		return temp2;
	}

	
	void set_pos_player(int[] pos){
		if (this.player == 'A'){
			this.currentPosA[0] = pos[0];
			this.currentPosA[1] = pos[1];
		}
		else{
			this.currentPosB[1] = pos[1];
			this.currentPosB[0] = pos[0];
		}
	}
	

	int [] get_oldpos_player(){
		if (this.player == 'A'){
			return oldPosA;
		}
		else{
			return oldPosB;
		}
	}
	void set_oldpos_player(int [] pos){
		if (this.player == 'A'){
			this.oldPosA[0] = pos[0];
			this.oldPosA[1] = pos[1];
		}
		else{
			this.oldPosB[0] = pos[0];
			this.oldPosB[1] = pos[1];	
		}
	}
	
	State get_parent(){
		return this.parent;
	}
	
	void set_parent(State parent){
		this.parent = parent;
	}
	
	void add_Child (State state){
		int i = 0;
		
		while (this.children[i] != null ){
			i++;
		}
		this.children[i] = state;
	}

	
	State[] get_children(){
		return this.children ;
	}

	
	void move(String mov, int num ){
		int temp[] = get_pos_player();
		int r = temp[0];
		int c = temp[1];
		char otherPlayer = ' ' ;
		
		// Determine which is the opposite player of the one playing.		
		if (get_player() == 'A') {
			otherPlayer = 'X';
		}else{
			otherPlayer = 'A';
		}

		if (mov == "up"){
			int temp1[] = {r-1,c};
			State child = new State(this.M,this.N,this.board);
			
			
			if( temp[0] != 0 && this.board[temp[0]-1][temp[1]] == 'W' && this.board[temp[0]-1][temp[1]] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);	
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);	
				
			}
		}
		else if (mov == "down"){
			int temp1[] = {r+1,c};
			State child = new State(this.M,this.N,this.board);
			if( temp[0] != M-1 && this.board[temp[0]+1][temp[1]] == 'W' && this.board[temp[0]+1][temp[1]] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);			
			}
		}
		else if (mov == "left"){
			int temp1[] = {r,c-1};
			State child = new State(this.M,this.N,this.board);;
			if( temp[1] != 0  && this.board[temp[0]][temp[1]-1] == 'W' && this.board[temp[0]][temp[1]-1] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);								
			}
		}
		else if (mov == "right"){
			int temp1[] ={r,c+1};
			State child = new State(this.M,this.N,this.board);
			if( temp[1] != N-1  && this.board[temp[0]][temp[1]+1] == 'W' && this.board[temp[0]][temp[1]+1] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);							
			}
		}
		else if (mov == "upleft"){
			int temp1[] = {r-1,c-1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != 0  && temp[0] != 0)  && this.board[temp[0]-1][temp[1]-1] == 'W' && this.board[temp[0]-1][temp[1]-1] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
			
			}
		}
		else if (mov == "upright"){
			int temp1[]= {r-1,c+1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != N-1  && temp[0] != 0)  && this.board[temp[0]-1][temp[1]+1] == 'W' && this.board[temp[0]-1][temp[1]+1] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
								
			}			
		}
		else if (mov == "downleft"){
			int temp1[]= {r+1,c-1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != 0  && temp[0] != M-1)  && this.board[temp[0]+1][temp[1]-1] == 'W' && this.board[temp[0]+1][temp[1]-1] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
			
			}
		}
		else if (mov == "downright"){
			int temp1[]= {r+1,c+1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != N-1  && temp[0] != M-1)  && this.board[temp[0]+1][temp[1]+1] == 'W' && this.board[temp[0]+1][temp[1]+1] != otherPlayer){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
								
			}
		}
	}
}


class Tree{
	String all_possible_moves[] = {"up","down","right","left","upright","upleft","downright","downleft"};
	int minimaxer (State state , char player){
		int score = 0;
		for (String i : this.all_possible_moves){
			state.move(i,1);
		}//Check win..
		
		//If the player has no other moves.
		if (!(state.hasChildren)){
			if (player == 'A') {
				score = -1;
			}else{
				score = 1;
			}
			return score;
			//Check who wins.
			//Return 1 or -1.
		}
		
		if (state.get_player() == 'A'){
			score = 2;
			for (State child : state.get_children()){
				if (child == null){
					continue;
				}
				
				int minScore = minimaxer(child ,'X');
				score = Math.min(score , minScore);		
			}
		}else{
			score = -2;
			for (State child : state.get_children()){
				if (child == null){
					continue;
				}
				
				int maxScore = minimaxer(child ,'A');
				score = Math.max(score , maxScore);	
			}
		}
		return score;
			
	}
}


class minimax{
	public static void main(String[] args){
		int score;
		int arow,acol,xrow,xcol ;
		Scanner input = new Scanner(System.in);


	
		System.out.println("Welcome to our game : ");
		System.out.print("Please give the number of rows: ");
		int row = input.nextInt();
		System.out.print("Now give the number of columns: ");
		int columns = input.nextInt();

		
		System.out.print("Please give the row you want player A to be:");
		arow = input.nextInt();
		while (arow < 0 || arow>=row){
			System.out.print("Please give a position into the scope of the row:");
			arow = input.nextInt();
			
		}
		
		System.out.print("Please give the column you want player A to be:");
		acol = input.nextInt();
		while (acol < 0 || acol>=columns){
			System.out.print("Please give a position into the scope of the column:");
			acol = input.nextInt();
		}


		System.out.print("Please give the row you want player X to be:");
		xrow = input.nextInt();
		while (xrow < 0 || xrow>=row ){
			System.out.print("Please give a position into the scope of the row: ");
			xrow = input.nextInt();
			
		}
	
		System.out.print("Please give the column you want player X to be:");
		xcol = input.nextInt();
		while (xcol < 0 || xcol>=columns || (xrow == arow && xcol == acol)){
			System.out.print("Please give a position into the scope of the row: ");
			xcol = input.nextInt();
			
		}

		char[][] board1 = new char[row][columns];
		for (int i = 0; i < row ; i++){
			for (int j = 0 ; j < columns ; j++){
					board1[i][j]= 'W' ;
			}
		}

		
				
		board1[arow][acol] = 'A';
		board1[xrow][xcol] = 'X';

		
		State root = new State(row,columns,board1) ;
		root.set_player('A');
		Tree game = new Tree();
		score = game.minimaxer(root , 'A');
		if (score == 1) {
			System.out.println("AI wins");
		}else{
			System.out.println("User wins");
		}
	}
}



	
	
