// Membuat data class untuk menyimpan data mahasiswa
// Data class ini memiliki 4 atribut: NIM, Nama, Mata Kuliah, dan Nilai
data class NilaiMahasiswa(
    val nim: String,
    val nama: String,
    val mataKuliah: String,
    val nilai: Int
)

// Fungsi untuk menentukan grade berdasarkan nilai
fun getGrade(nilai:Int): String{
    return when(nilai){
        in 85..100 -> "A"   // Nilai 85 - 100 mendapatkan grade A
        in 70..84 -> "B"    // Nilai 70 - 84 mendapatkan grade B
        in 60..69 -> "C"    // Nilai 60 - 69 mendapatkan grade C
        in 50..59 -> "D"    // Nilai 50 - 59 mendapatkan grade D
        else -> "E"         // Nilai dibawah 50 mendapatkan grade E
    }
}

fun main(){
    println("Muhammad Arafa Yahfazhka")
    println("F1D02310126")

    // Menampilkan judul program
    println("===== DATA NILAI MAHASISWA =====")
    println()

    // Membuat list berisi data beberapa mahasiswa
    val dataMahasiswa =  listOf(
        NilaiMahasiswa("2024001","Budi Santoso","Pemrograman",85),
        NilaiMahasiswa("2024002","Ani Wijaya","Pemrograman",92),
        NilaiMahasiswa("2024003","Citra Dewi","Pemrograman",68),
        NilaiMahasiswa("2024004","Dani Pratama","Pemrograman",45),
        NilaiMahasiswa("2024005","Eka Putri","Pemrograman",75),
        NilaiMahasiswa("2024006","Fajar Nugraha","Pemrograman",80),
        NilaiMahasiswa("2024007","Gina Lestari","Pemrograman",55),
        NilaiMahasiswa("2024008","Hadi Saputra","Pemrograman",90),
        NilaiMahasiswa("2024009","Indah Sari","Pemrograman",60),
        NilaiMahasiswa("2024010","Joko Prasetyo","Pemrograman",72)
    )

    // Menampilkan header tabel
    println("%-3s %-8s %-15s %-15s %-5s".format("No", "NIM", "Nama", "MataKuliah", "Nilai"))

    // Variabel nomor urut
    var no = 1

    // Loop untuk menampilkan semua data mahasiswa dalam bentuk tabel
    for (mhs in dataMahasiswa) {
        println("%-3d %-8s %-15s %-15s %-5d".format(
            no,
            mhs.nim,
            mhs.nama,
            mhs.mataKuliah,
            mhs.nilai
        ))
        no++
    }

    println()
    println("==== STATISTIK ====")

    // Menghitung rata-rata nilai mahasiswa
    val rata = dataMahasiswa.map {it.nilai}.average()

    println("Total Mahasiswa : ${dataMahasiswa.size}")
    println("Rata-rata Nilai : $rata")

    // Mencari mahasiswa dengan nilai tertinggi
    val tertinggi = dataMahasiswa.maxByOrNull {it.nilai}
    println("Nilai Tertinggi : ${tertinggi?.nilai} (${tertinggi?.nama})")

    // Mencari mahasiswa dengan nilai terendah
    val terendah = dataMahasiswa.minByOrNull {it.nilai}
    println("Nilai Terendah : ${terendah?.nilai} (${terendah?.nama})")

    println()
    println("==== MAHASISWA LULUS ====")

    // Menyaring mahasiswa yang lulus (nilai >= 70)
    val lulus = dataMahasiswa.filter {it.nilai >= 70}

    for (mhs in lulus){
        println("${mhs.nama} - ${mhs.nilai} - (${getGrade(mhs.nilai)})")
    }

    println()
    println("==== MAHASISWA TIDAK LULUS ====")

    // Menyaring mahasiswa yang tidak lulus (nilai < 70)
    val tidakLulus = dataMahasiswa.filter {it.nilai < 70}

    for (mhs in tidakLulus){
        println("${mhs.nama} - ${mhs.nilai} - (${getGrade(mhs.nilai)})")
    }

    println()
    println("==== URUT NILAI ASCENDING ====")

    // Mengurutkan data mahasiswa berdasarkan nilai dari kecil ke besar
    val asc = dataMahasiswa.sortedBy {it.nilai}

    for (mhs in asc){
        println("${mhs.nama} - ${mhs.nilai}")
    }

    println()
    println("==== URUT NILAI DESCENDING ====")

    // Mengurutkan data mahasiswa berdasarkan nilai dari besar ke kecil
    val desc = dataMahasiswa.sortedByDescending {it.nilai}

    for (mhs in desc){
        println("${mhs.nama} - ${mhs.nilai}")
    }

    println()
    println("==== KELOMPOK BERDASARKAN GRADE ====")

    // Mengelompokkan mahasiswa berdasarkan grade menggunakan fungsi groupBy
    val kelompok = dataMahasiswa.groupBy {getGrade(it.nilai)}

    for ((grade, list) in kelompok){
        println("Grade $grade : ")
        for(mhs in list){
            println(" - ${mhs.nama} (${mhs.nilai})")
        }
    }

    println()
    println("==== JUMLAH PER GRADE ====")

    // Menampilkan jumlah mahasiswa pada setiap grade
    for ((grade, list) in kelompok){
        println("Grade $grade : ${list.size} mahasiswa")
    }

    println()
    println("==== CARI MAHASISWA (nama mengandung 'an') ====")

    // Mencari mahasiswa yang namanya mengandung kata "an"
    val cari = dataMahasiswa.filter {it.nama.contains("an")}

    for (mhs in cari){
        println("${mhs.nama} - ${mhs.nilai}")
    }
}