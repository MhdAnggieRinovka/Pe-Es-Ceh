/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Denise ,Mhd Anggie,  Rajasa Cikal
 */
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class RushHour {
	
	public Scanner in; //atribut scanner
	
	public PriorityQueue<State> pq; //atribut PriorityQueue dengan tipe State
	
	private LinkedList<State> searchAStar(Puzzle puzzle, Heuristic heuristic) { //method searchAStar, merupakan method untuk mencari A* dengan parameter puzzle dan heuristic
		HashMap<State, State> predecessor = new HashMap<>(); //menginisialisasi HasMap dengan nama predecessor dengan key berupa State dan value berupa State
		HashMap<String, Boolean> visited = new HashMap<>();//menginisialisasi HasMap dengan nama visited dengan key berupa String dan value berupa boolean
		State src = new State(puzzle); //membuat state baru src dengan parameter puzzle
		State goal = null; //membuat state goal dengan inisialisasi value berupa null
		src.setCost(0); //memasukan cost awal pada src dengan 0
		pq.add(src); //memasukan state src kedalam PriorityQueue 
		visited.put(src.toString(), true); //menyimpan key state src, dan value true kedalam HashMap visited, untuk menandakan bahwa state src telah di visit
		while(!pq.isEmpty()){ //looping selama PriorityQueue belum kosong
			State u = pq.poll(); //membuat state baru dengan nama u, yang berisi pengambilan elemen pertama dari antrian PriorityQueue pq
			if(u.isGoal()){ //jika state u merupakan state goal dengan memanbbil isGoal() dari kelas State
				goal = u; //mengisi state goal dengan state u yang merupakan elemen pertama dari PriorityQueue pq
				break;
			}
			for(State v : u.getNeighbors()){ //melakukan looping for each, dengan memasukan tetangga dari state u kedalam state baru bernama state v
				int cost = u.cost + 1 + heuristic.getValue(v); //membuat cost yang berisi cost dari state u + 1 + value dari movement heuristic
				if(!contains(visited, v)){ //jika  v belum di visit
					v.setCost(cost); //menyimpan cost dari state v dengan int cost dibaris 39
					pq.add(v); //menambah state v kedalam PriorityQueue pq ke urutan paling belakang
					predecessor.put(v, u); //memasukan state v sebagai key dan state u sebagai value kedalam hashmap predecessor
					visited.put(v.toString(), true); //membuat state v yang sekarang, sudah di visit
				}
			}
		}
		
		return getPath(predecessor, goal); //mereturn getPath dengan parameter hashmap predecessor dan state goal
	}

	private boolean contains(HashMap<String, Boolean> visited, State v) {
		return visited.containsKey(v.toString());
	}

	private void print(LinkedList<State> path) {
		System.out.println("Number of optimal movements = " + (path.size()-1) + "\n"); //print berapa banyak solusi yang ditemukan
		int index = 0; //menghitung berapa banyak langkah yang telah ditemukan
		for(State s : path){ //looping untuk object State yang bernama path
			if(index == 0) // jika index = 0
				System.out.println("Initial state:"); // maka print Intial state
                        else // jika tidak
				System.out.println("Step " + index + ":"); // maka print Step + index + :
			s.print(); //pemanggilan method print di kelas State
			index ++; //index ditambah 1
		}
	}

	private LinkedList<State> getPath(HashMap<State, State> pred, State goal) {
		
		LinkedList<State> path =  new LinkedList<>(); //Membuat linklist bertipe State
		State u = goal; //membuat variable baru yang bertipe State yang diisi dengan parameter yang bernama goal
		path.addFirst(u.clone()); //untuk menambahkan State dibagian pertama node
		while(pred.get(u) != null){ //ketika nilai pred yang isinya adalah variable u tidak sama dengan kosong
			State parent = pred.get(u); // variable parent yang bertipe State diisi dengan pred yang memiliki isi variable u
			path.addFirst(parent.clone()); //lalu node pertama pada variable path diisi dengan tiruan dari variable parent
			u = parent; //variable u diisi dengan nilai dari variable parent
		}

		return path; // mengembalikan nilai path
	}

	private Puzzle readInput() {
		
		LinkedList<Car> cars = new LinkedList<>(); //Membuat linklist yang bertipe car
		
		String line = in.nextLine(); //menscan baris selanjutnya
		int size = line.length(); //mengisi nilai size dengan nilai panjang string line di baris 87
		char[][] grid = new char[size][size]; //membuat array of char, dengan panjang size yang didapat pada baris 88
		grid[0] = line.toCharArray(); //memasukan array grid ke 0, dengan mengubah string line di baris 87, kedalam char
		for (int i = 1; i < grid.length; i++) { //melakukan looping sebanyak panjang grid dengan tujuan memasukan baris per baris stirng line,
												//kedalam grid ke i.
			line = in.nextLine();
			grid[i] = line.toCharArray();
		}

		for (int i = 0; i < grid.length; i++) { //melakukan looping
			for (int j = 0; j < grid.length; j++) {
				switch (grid[i][j]) {
					case '+': grid[i+1][j] = '.'; cars.add(new Car(i, j, "v", 2)); break; //case akan dijalankan jika grid ke (i+1,j) adalah sebuah mobil vertical dengan size 2
																						  //yang dilambangkan dengan '+', lalu memasukan car baru kedalam linkedlist dengan parameter x = i, y=j, orientasi 'v' = vertical
																						  //dan ukuran mobil = 2
					case '*': grid[i][j+1] = '.'; cars.add(new Car(i, j, "h", 2)); break;//case akan dijalankan jika grid ke (i,j+1) adalah sebuah mobil horizontal dengan size 2
																						  //yang dilambangkan dengan '*', lalu memasukan car baru kedalam linkedlist dengan parameter x = i, y=j, orientasi 'h' = horizontal
																						  //dan ukuran mobil = 2
					case '@': grid[i+1][j] = '.'; grid[i+2][j] = '.'; cars.add(new Car(i, j, "v", 3)); break;//case akan dijalankan jika grid ke (i+1,j) adalah sebuah mobil vertical dengan size 3
																						  //yang dilambangkan dengan '@', lalu memasukan car baru kedalam linkedlist dengan parameter x = i, y=j, orientasi 'v' = vertical
																						  //dan ukuran mobil = 3
					case '#': grid[i][j+1] = '.'; grid[i][j+2] = '.'; cars.add(new Car(i, j, "h", 3)); break;//case akan dijalankan jika grid ke (i,j+1) adalah sebuah mobil horizontal dengan size 3
																						  //yang dilambangkan dengan '#', lalu memasukan car baru kedalam linkedlist dengan parameter x = i, y=j, orientasi 'v' = horizontal
																						  //dan ukuran mobil = 3
					case '=': grid[i][j+1] = '.'; cars.addFirst(new Car(i, j, "h", 2)); break; //case akan berjalan jika grid ke (i,j+1) = "=", dimana adalah sebuah red car
				}
			}
		}
		
		return new Puzzle(size, cars); //kembaliannya memanggil sebuah kelas puzzle baru, dengan parameter size pada baris 88, dan cars yang berisi kumpulan string mobil yang telah ditambahkan pada looping baris 97 sampai 116
	}
	
	private void run() { //method run dipanggil di method main pada kelas ini (RushHour)
		pq = new PriorityQueue<State>(10, new Comparator<State>() { //membuat PriorityQueue baru
			
			@Override //mengoverride compare dari Comparator
			public int compare(State o1, State o2) { //melakukan compare antara 01 dan 02
				return o1.cost - o2.cost; //mengembalikan cost yang merupakan hasil dari cost 01 dikurang cos 02
			}
			
		});
		
		in = new Scanner(System.in); //menginisialisasi in dengan scanner
		
		Puzzle puzzle = readInput(); //memasukan hasil dari method readInput() kedalam puzzle
		
		Heuristic heuristic1 = new Heuristic1(); //menginisialisasi Heuristic1
		Heuristic heuristic2 = new Heuristic2(); //menginisialisasi Heuristic2 
		
		long startTime = System.currentTimeMillis(); //memasukan current time dalam milisecond ke dalam startTime sebagai waktu awal dimulai dari dijalankannya method searchAStar
		LinkedList<State> path1 = searchAStar(puzzle, heuristic1); //menjalankan method searchAStar dengan parameter puzzle dan heuristic1
		long endTime = System.currentTimeMillis(); //memasukan current time dalam milisecond ke dalam endTime sebagai waktu berhenti dari dijalankannya method searchAStar
		long timeTaken1 = endTime - startTime; //mencari total waktu dijalankannya program dengan mengurangi waktu berhenti dengan waktu mulai dan dimasukan kedalam timeTaken1
		
		startTime = System.currentTimeMillis();//memasukan current time dalam milisecond ke dalam startTime sebagai waktu awal dimulai dari dijalankannya method searchAStar
		LinkedList<State> path2 = searchAStar(puzzle, heuristic2);//menjalankan method searchAStar dengan parameter puzzle dan heuristic2
		endTime = System.currentTimeMillis();//memasukan current time dalam milisecond ke dalam endTime sebagai waktu berhenti dari dijalankannya method searchAStar
		long timeTaken2 = endTime - startTime;//mencari total waktu dijalankannya program dengan mengurangi waktu berhenti dengan waktu mulai dan dimasukan kedalam timeTaken1
		
		System.out.println("Solution using Heuristic."); //mengeprint ke layar 
		System.out.println("#########################"); //mengeprint ke layar
		print(path1); //mengeprint ke layar LinkedList dari path1
		System.out.println("Time taken using heuristic : " + timeTaken1); //mengeprint ke layar berupa waktu yang dipakai saat menjalankan heuristic1
		System.out.println();
		
		System.out.println("Solution with no Heuristic used.");//mengeprint ke layar
		System.out.println("################################");//mengeprint ke layar
		print(path2);//mengeprint ke layar LinkedList dari path2
		System.out.println("Time taken without using heuristic : " + timeTaken2);//mengeprint ke layar berupa waktu yang dipakai saat menjalankan heuristic2
		
		in.close(); //memanggil method close dari scanner
	}
	
	public static void main(String[] args) { //kelas main dari RushHour
		
		RushHour THIS = new RushHour(); //menginisialisasi kelas RushHour dengan nama THIS
		THIS.run(); //memanggil method run dari kelas RushHour
	}
}
