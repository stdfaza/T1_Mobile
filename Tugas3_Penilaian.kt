fun main(){
    println("Muhammad Arafa Yahfazhka")
    println("F1D02310126")

    // Menampilkan judul program
    println("===== SISTEM PENILAIAN =====")

    // ===============================
    // Bagian Input Data Mahasiswa
    // ===============================

    // Meminta pengguna memasukkan nama mahasiswa
    print("Masukkan Nama Mahasiswa: ")
    val name = readln()

    // Meminta input nilai UTS lalu mengubahnya ke tipe Double
    // Jika input tidak valid maka akan bernilai 0.0
    print("Masukkan Nilai UTS(0-100): ")
    val midterm = readln()?.toDoubleOrNull() ?: 0.0

    // Validasi jika nilai lebih dari 100 maka program berhenti
    if (midterm > 100) {
        println("Salah kamu keluar")
        return
    }

    // Meminta input nilai UAS
    print("Masukkan Nilai UAS(0-100): ")
    val finalexam = readln()?.toDoubleOrNull() ?: 0.0

    // Validasi nilai UAS tidak boleh lebih dari 100
    if (finalexam > 100) {
        println("Salah kamu keluar")
        return
    }

    // Meminta input nilai Tugas
    print("Masukkan Nilai Tugas(0-100): ")
    val Task = readln()?.toDoubleOrNull() ?: 0.0

    // Validasi nilai tugas
    if (Task > 100) {
        println("Salah kamu keluar")
        return
    }

    println()

    // ===============================
    // Bagian Menampilkan Data Nilai
    // ===============================

    println("===== Hasil Penilaian =====")
    println("Nama       : $name")
    println("Nilai UTS  : $midterm (Bobot 30%)")
    println("Nilai UAS  : $finalexam (Bobot 40%)")
    println("Nilai Tugas: $Task (Bobot 30%)")
    println("------------------------------")

    // Menghitung nilai akhir berdasarkan bobot
    // UTS = 30%, UAS = 40%, Tugas = 30%
    val final_grade = (midterm * 0.3) + (finalexam * 0.4) + (Task * 0.3)

    // ===============================
    // Menentukan Grade Huruf
    // ===============================

    // Menggunakan when untuk menentukan grade
    val grade = when (final_grade){
        in 85.0..100.0 -> "A"
        in 70.0..<85.0 -> "B"
        in 60.0..<70.0 -> "C"
        in 50.0..<60.0 -> "D"
        else -> "E"
    }

    // Menentukan deskripsi dari grade
    val desc = when (grade){
        "A" -> "Sangat Baik"
        "B" -> "Baik"
        "C" -> "Cukup"
        "D" -> "Kurang"
        else -> "Sangat Kurang"
    }

    // ===============================
    // Menentukan Status Kelulusan
    // ===============================

    // Jika nilai akhir kurang atau sama dengan 60 maka LULUS
    // Jika lebih dari 60 maka TIDAK LULUS
    val status = if (final_grade <= 60){
        "LULUS"
    }else{
        "TIDAK LULUS"
    }

    // Menampilkan hasil akhir
    println("Nilai Akhir : $final_grade")
    println("Grade       : $grade")
    println("Keterangan  : $desc")
    println("Status      : $status")
    println()

    // ===============================
    // Pesan Akhir Program
    // ===============================

    // Jika status lulus maka tampilkan pesan selamat
    if (status == "LULUS"){
        println("Selamat! Anda dinyatakan lulus")
    }else{
        println("TypeShiiiii")
    }
}