/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS FX504 //Test
 */

public class Car {
	
	int x, y, size;
	String orientation;
	
	public Car(int x, int y, String orientation, int size) {
		super();
		this.x = x; //x sebagai titik baris pada board
		this.y = y; //y sebagai titik kolom pada board
		this.size = size; //menyatakan ukuran dari puzzle 
		this.orientation = orientation; //menyatakan orientasi apakah horizontal atau vertical
	}
	
	public void moveDown() {//posisi x berpindah kebawah (baris pindah kebawah) dan posisi kolom tidak berubah
		this.x ++; //nilai x ditambah 1
	}

	public void moveUp() {//posisi x berpindah keatas (baris pindah keatas) dan posisi kolom tidak berubah
		this.x --; //nilai x dikurang 1
	}

	public void moveRight() {//posisi y berpindah kekanan (kolom pindah kekanan) dan posisi baris tidak berubah
		this.y ++; //nilai y ditambah 1
	}

	public void moveLeft() { //posisi y berpindah kekiri (kolom pindah kekanan) dan posisi baris tidak berubah
		this.y --; //nilai y dikurang 1
	}

	public boolean isHorizontal() { //menentukan apakah orientasi adalah horizontal
		return orientation.equals("h"); //mengembalikan true jika orientasi = horizontal
	}

	public boolean isVertical() { //menentukan apakah orientasi adalah vertical
		return orientation.equals("v"); //mengembalikan true jika orientasi = vertical
	}
	
	public Car clone(){ //membuat object mobil baru beserta posisi-posisinya
		return new Car(this.x, this.y, this.orientation, this.size); //mengembalikan object mobil baru dengan posisi yang telah dibuat
	}
}
