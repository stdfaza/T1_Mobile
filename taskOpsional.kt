// Interface untuk produk yang bisa memiliki diskon
interface Discountable {
    fun getDiscountedPrice(): Double
}

// Data class Produk yang merepresentasikan barang di toko
// Mengimplementasikan interface Discountable
data class Produk(
    val id: Int,
    val nama: String,
    val harga: Double,
    val kategori: String,
    var stok: Int,
    val diskon: Double = 0.0
) : Discountable {

    // Menghitung harga setelah diskon
    override fun getDiscountedPrice(): Double {
        return harga - (harga * diskon)
    }
}

// Data class untuk item dalam keranjang belanja
data class CartItem(
    val produk: Produk,
    var jumlah: Int
)

// Data class untuk menyimpan informasi customer
data class Customer(
    val id: Int,
    val nama: String,
    val email: String,
    val alamat: String?
)

// Sealed class untuk status order
// Membatasi status hanya pada pilihan berikut
sealed class OrderStatus {
    object Pending : OrderStatus()
    object Processing : OrderStatus()
    object Shipped : OrderStatus()
    object Delivered : OrderStatus()
    object Cancelled : OrderStatus()
}

// Sealed class untuk metode pembayaran
sealed class PaymentMethod {
    object Cash : PaymentMethod()
    object Transfer : PaymentMethod()
    object EWallet : PaymentMethod()
}

// Data class untuk menyimpan informasi pesanan
data class Order(
    val id: Int,
    val customer: Customer,
    val items: List<CartItem>,
    var status: OrderStatus,
    val paymentMethod: PaymentMethod,
    val totalHarga: Double
)

// Class ShoppingCart untuk mengelola keranjang belanja
class ShoppingCart {

    // List mutable untuk menyimpan item dalam keranjang
    val items = mutableListOf<CartItem>()

    // Menambahkan produk ke keranjang
    fun tambahProduk(produk: Produk, jumlah: Int) {

        // Mengecek apakah produk sudah ada di keranjang
        val existing = items.find { it.produk.id == produk.id }

        if (existing != null) {
            // Jika sudah ada, tambah jumlahnya
            existing.jumlah += jumlah
        } else {
            // Jika belum ada, tambahkan item baru
            items.add(CartItem(produk, jumlah))
        }

        println("${produk.nama} ditambahkan ke keranjang")
    }

    // Menghapus produk dari keranjang berdasarkan id
    fun hapusProduk(idProduk: Int) {
        items.removeIf { it.produk.id == idProduk }
        println("Produk dihapus dari keranjang")
    }

    // Menghitung total harga dengan parameter fungsi diskon
    fun hitungTotal(discountCalculator: (Double) -> Double): Double {

        // Menjumlahkan semua harga produk di keranjang
        val total = items.sumOf {
            it.produk.getDiscountedPrice() * it.jumlah
        }

        // Mengembalikan total setelah diskon tambahan
        return discountCalculator(total)
    }

    // Menampilkan isi keranjang
    fun tampilkanCart() {
        println("===== ISI KERANJANG =====")
        for (item in items) {
            println("${item.produk.nama} x${item.jumlah} = ${item.produk.harga * item.jumlah}")
        }
    }
}

// Class utama untuk sistem toko online
class TokoOnline {

    // List produk yang tersedia di toko
    val daftarProduk = mutableListOf<Produk>()

    // List riwayat pesanan
    val riwayatOrder = mutableListOf<Order>()

    // Menambahkan produk ke toko
    fun tambahProduk(produk: Produk) {
        daftarProduk.add(produk)
    }

    // Menampilkan semua produk
    fun tampilProduk() {
        println("===== DAFTAR PRODUK =====")

        daftarProduk.forEach {
            println("${it.id} ${it.nama} - ${it.harga} (stok: ${it.stok})")
        }
    }

    // Mencari produk berdasarkan nama
    fun cariProduk(nama: String): List<Produk> {
        return daftarProduk.filter {
            it.nama.contains(nama, ignoreCase = true)
        }
    }

    // Mengurutkan produk berdasarkan harga
    fun urutkanHarga() {
        val sorted = daftarProduk.sortedBy { it.harga }

        println("===== PRODUK DIURUTKAN BERDASARKAN HARGA =====")

        sorted.forEach {
            println("${it.nama} - ${it.harga}")
        }
    }

    // Mengelompokkan produk berdasarkan kategori
    fun kelompokKategori() {
        val grouped = daftarProduk.groupBy { it.kategori }

        println("===== PRODUK PER KATEGORI =====")

        for ((kategori, list) in grouped) {
            println("Kategori: $kategori")

            for (p in list) {
                println(" - ${p.nama}")
            }
        }
    }

    // Proses checkout
    fun checkout(
        cart: ShoppingCart,
        customer: Customer,
        paymentMethod: PaymentMethod
    ) {

        // Menghitung total harga keranjang
        val total = cart.hitungTotal { it }

        // Membuat order baru
        val order = Order(
            id = riwayatOrder.size + 1,
            customer = customer,
            items = cart.items.toList(),
            status = OrderStatus.Pending,
            paymentMethod = paymentMethod,
            totalHarga = total
        )

        // Menyimpan order ke riwayat
        riwayatOrder.add(order)

        println("Checkout berhasil. Total: $total")
    }

    // Menampilkan riwayat pesanan
    fun tampilRiwayat() {
        println("===== RIWAYAT ORDER =====")

        for (order in riwayatOrder) {
            println("Order ${order.id} - ${order.totalHarga}")
        }
    }
}

// Fungsi utama program
fun main() {
    println("Muhammad Arafa Yahfazhka")
    println("F1D02310126")

    // Membuat objek toko
    val toko = TokoOnline()

    // Membuat beberapa produk
    val produk1 = Produk(1, "Laptop", 8000000.0, "Elektronik", 10, 0.1)
    val produk2 = Produk(2, "Mouse", 200000.0, "Elektronik", 20)
    val produk3 = Produk(3, "Keyboard", 350000.0, "Elektronik", 15)

    // Menambahkan produk ke toko
    toko.tambahProduk(produk1)
    toko.tambahProduk(produk2)
    toko.tambahProduk(produk3)

    // Menampilkan produk
    toko.tampilProduk()

    println()

    // Membuat keranjang belanja
    val cart = ShoppingCart()

    // Menambahkan produk ke keranjang
    cart.tambahProduk(produk1, 1)
    cart.tambahProduk(produk2, 2)

    // Menampilkan isi keranjang
    cart.tampilkanCart()

    // Menghitung total dengan diskon tambahan 10%
    val total = cart.hitungTotal { total ->
        total * 0.9
    }

    println("Total setelah diskon: $total")

    println()

    // Membuat data customer
    val customer = Customer(
        1,
        "Budi Santoso",
        "budi@email.com",
        null
    )

    // Proses checkout
    toko.checkout(cart, customer, PaymentMethod.Transfer)

    println()

    // Menampilkan riwayat order
    toko.tampilRiwayat()

    println()

    // Menampilkan produk yang sudah diurutkan berdasarkan harga
    toko.urutkanHarga()

    println()

    // Menampilkan produk berdasarkan kategori
    toko.kelompokKategori()
}