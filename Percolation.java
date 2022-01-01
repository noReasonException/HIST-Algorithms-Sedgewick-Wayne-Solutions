 


import java.util.Random;

public class Percolation {
    private  int OpenedSpots=0;
    private int Size=0;
    private boolean Grid[][];
    private int Set[];
    private int Max[];
    private int getSize(){
        return this.Size;

    }
    private double blocks(){
        return Math.pow(this.getSize(),2);
    }
    private void Init(){
        for (int i = 0; i <this.Size ; i++) {
            for (int j = 0; j <this.Size ; j++) {
                this.Grid[i][j]=false;
            }
        }
        for (int i = 0; i < this.Size * this.Size; i++) {
            this.Max[i]=i;
        }
        for (int i = 0; i < Math.pow(this.Size,2); i++) {
            this.Set[i]=i;
        }
        for (int i = 0; i <this.Size ; i++) {
            this.open(0,i);
        }
        for (int i = 0; i <this.Size ; i++) {
            this.open(this.Size-1,i);
        }


    }
    private int getRepresentorOf(int Element){
        if(this.Set[Element]!=Element)return this.getRepresentorOf(this.Set[Element]);
        return Element;
    }
    private void updateMaxOf(int Representor1,int Representor2){
        this.Max[Representor1]=this.Max[Representor2];
    }
    private void Connect(int Node1,int Node2){
        int representorOfNode1=this.getRepresentorOf(Node1);
        int representorOfNode2=this.getRepresentorOf(Node2);
        if(this.Max[representorOfNode1]>this.Max[representorOfNode2]){
            this.Set[representorOfNode1]=this.Set[representorOfNode2];
            this.updateMaxOf(representorOfNode1,representorOfNode2);
        }
        else{
            this.Set[representorOfNode2]=this.Set[representorOfNode1];
            this.updateMaxOf(representorOfNode2,representorOfNode1);

        }
        this.Set[representorOfNode1]=this.Set[representorOfNode2];

        //return;
    }
    private boolean isConnected(int Node1,int Node2)throws java.lang.IllegalArgumentException{

        return this.Set[this.Set[Node1]=this.getRepresentorOf(Node1)]==this.Set[this.Set[Node2]=this.getRepresentorOf(Node2)];
    }
    public Percolation(int n) throws java.lang.IllegalArgumentException{
        if(n<0)throw new java.lang.IllegalArgumentException();
        this.Size=n;
        this.Grid=new boolean[n][n];
        this.Set=new int[n*n];
        this.Max=new int[n*n];
        this.Init();

    }/*
    private void Debug(){
        for (int i = 0; i < this.Size; i++) {
            for (int j = 0; j < this.Size; j++) {
                System.out.print("-|"+(this.Grid[i][j]?"1":"0")+"|");
            }
            System.out.print("\n");

        }
        System.out.print("\n");
        for (int i = 0; i < this.Size*this.Size; i++) {
            System.out.print("-|"+this.Set[i]+"|");
        }
        System.out.print("\n");
        for (int i = 0; i < this.Size*this.Size; i++) {
            System.out.print("-|"+i+"|");
        }
        System.out.print("\n");

    }
    private void FillDebug(){
        this.OpenedSpots=this.Size*this.Size;
        for (int i = 0; i < this.Size-1; i++) {
            for (int j = 0; j < this.Size-1; j++) {
                this.open(i,j);
            }
        }

    }*/
    private int toID(int row,int col){
        return row*this.Size+col;
    }
    private int[] to2D(int ID){
        int []tmp=new int[2];
        tmp[0]=ID/this.Size;
        tmp[1]=ID&this.Size;
        return tmp;
    }
    public void open(int row,int col)throws java.lang.ArrayIndexOutOfBoundsException{
        this.Grid[row][col]=true;
        this.OpenedSpots+=1;
        if(row>0 &&this.Grid[row-1][col]){
            this.Connect(this.toID(row,col),this.toID(row-1,col));
        }
        if(row<this.Size-1 &&this.Grid[row+1][col]){
            this.Connect(this.toID(row,col),this.toID(row+1,col));
        }
        if(col>0 &&this.Grid[row][col-1]){
            this.Connect(this.toID(row,col),this.toID(row,col-1));
        }
        if(col<this.Size-1 &&this.Grid[row][col+1]){
            this.Connect(this.toID(row,col),this.toID(row,col+1));
        }
        if(col<this.Size-1 &&row<this.Size-1 &&this.Grid[row+1][col+1]){
            this.Connect(this.toID(row,col),this.toID(row+1,col+1));
        }
        if(col>1&&row>1 &&this.Grid[row-1][col-1]){
            this.Connect(this.toID(row,col),this.toID(row-1,col-1));
        }
        if(col<this.Size-1 &&row>1 &&this.Grid[row-1][col+1]){
            this.Connect(this.toID(row,col),this.toID(row-1,col+1));
        }
        if(col>1 &&row<this.Size-1 &&this.Grid[row+1][col-1]){
            this.Connect(this.toID(row,col),this.toID(row+1,col-1));
        }

    }
    public boolean isOpen(int row,int col)throws java.lang.ArrayIndexOutOfBoundsException{
        return this.Grid[row][col];
    }
    private boolean isFull(){
        return this.OpenedSpots==Math.pow(this.Size,2);
    }
    public boolean isFull(int a,int b){
        return this.Grid[a][b];
    }
    public int numberOfOpenSites(){
        return this.OpenedSpots;
    }
    public boolean percolates(){
        return this.isConnected(1,this.toID(this.Size-1,this.Size-1));
    }
    public static void main(String[] args) {
    }
}
