
import java.util.*;
import java.io.IOException;

class main{
	
	public static void main(String[] args){
		int maxX=8,maxY=8, bombs=10;
		int table[][]=new int[maxX+1][maxY+1];
		int bombplace[][]=new int[maxX+1][maxY+1];
		Scanner sc=new Scanner(System.in);
		
		
		while(true){
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				table[i][j]=0;
			}
		}
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				bombplace[i][j]=0;
			}
		}
	
		
		for(int i=0;i<bombs;i++){
		int bombX=-1;
		int bombY=-1;
		boolean go=true;
		do{
		go=true;
		bombX =getRandomIntBetweenRange(0,maxX);
		bombY=getRandomIntBetweenRange(0,maxY);
		
		bombplace[bombX][bombY]=1;
		//bombplace[1][1]=1;
		
		for(int ii=0;ii<maxX;ii++){
			for(int jj=0;jj<maxY;jj++){
				if(bombplace[ii][jj]==1&&table[bombX][bombY]==2)go=false;
			}
		}
		
		table[bombX][bombY]=2;
		//table[1][1]=2;
		
		// System.out.println(bombX+":"+bombY);
		}while(!go);
		}
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				if(table[i][j]!=2){
					int bombn=ScanBomb(i,j,table,maxX,maxY);
					if(bombn!=0)
						System.out.print(bombn+" ");
					else
						System.out.print("  ");
				}
				else
					System.out.print("M ");
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
			//	System.out.print(table[i][j]);
				if(table[i][j]==0)
					System.out.print(". ");
				else if(table[i][j]==1)
					System.out.print("o ");
				else if(table[i][j]==2)
				    System.out.print("! ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.print("Your Answer: ");
		String answer=sc.nextLine();
		
		System.out.println("\n\n");
		clrscr();
		}
	}
	public static int ScanBomb(int a,int b,int table[][],int maxX,int maxY){
		int bombNumber=0;
		//int a=2;int b=2;
		int ap=a+1,bp=b+1;
		int am=a-1,bm=b-1;
		//table[a][b]=1;
		if(ap<maxY){
	    if(table[ap][b]==2)bombNumber++;
	    if(bp<maxX)
	    if(table[ap][bp]==2)bombNumber++;
	    if(bm>=0)
	    if(table[ap][bm]==2)bombNumber++;
		}
		if(am>=0){
		if(table[am][b]==2)bombNumber++;
		if(bp<maxX)
	    if(table[am][bp]==2)bombNumber++;
	    if(bm>=0)
	    if(table[am][bm]==2)bombNumber++;
		}
		if(bp<maxX)
		if(table[a][bp]==2)bombNumber++;
		if(bm>=0)
		if(table[a][bm]==2)bombNumber++;
		return bombNumber;
	}
	public static int getRandomIntBetweenRange(int min, int max){
		Random random = new Random();
		int randomInteger = random.nextInt(max);
		return randomInteger;
	}
	public static void clrscr(){
		//Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {}
	} 
}