# AtmaMart

Aplikasi desktop point-of-sale (POS) untuk pengelolaan produk, kasir, pembelian, dan pengembalian barang pada toko/mart.

- **Desktop**: Java Swing (JDK 11)
- **Database**: MySQL 8.4 (Laragon)
- **Driver**: MySQL Connector/J 8.4.0
- **Build**: NetBeans / Apache Ant

Aplikasi ini bersifat **single-user/local** dan tidak memiliki login atau pembagian role. Seluruh fitur diakses dari menu utama `MainView`.

Nama proyek NetBeans: `E_7_Mart`.

## Arsitektur

```text
MainView / Panel (Java Swing)
             |
          Control
             |
          Service
             |
            DAO
             |
  JDBC Connector/J 8.4.0
             |
    MySQL 8.4 (Laragon)
```

## Struktur Repository

```text
AtmaMart/
├── Main/
│   ├── src/
│   │   ├── connection/    Koneksi JDBC ke MySQL
│   │   ├── control/       Penghubung panel dengan DAO
│   │   ├── dao/           Operasi CRUD dan transaksi database
│   │   ├── exception/     Exception validasi input
│   │   ├── interfaceDAO/  Kontrak method DAO
│   │   ├── model/         Entity Produk, Kasir, Transaksi, Detail_transaksi
│   │   ├── panel/         Panel Swing: Produk, Kasir, Pembelian, Pengembalian
│   │   ├── service/       Logika bisnis pembelian dan pengembalian
│   │   ├── table/         Table model untuk JTable
│   │   └── view/          MainView (entry point)
│   ├── lib/               MySQL Connector/J, commons-lang3, jcalendar
│   ├── nbproject/         Konfigurasi proyek NetBeans
│   ├── build.xml          Build script Ant
│   ├── manifest.mf
│   └── build/             Hasil kompilasi (generated)
├── database/
│   └── db_mart.sql        Skema + data awal db_mart
├── .github/               Tooling tambahan (opsional)
└── README.md
```

## Fitur Utama

### Produk

- Menampilkan daftar produk.
- Mencari produk berdasarkan ID atau nama.
- Menambah, mengubah, dan menghapus produk.
- Validasi input nama, tipe, harga, dan stok.
- Menampilkan produk yang stoknya masih tersedia pada menu pembelian.

### Kasir

- Menampilkan daftar kasir.
- Mencari kasir berdasarkan ID atau nama.
- Menambah, mengubah, dan menghapus kasir.

### Pembelian

- Memilih produk dan jumlah yang dibeli.
- Validasi jumlah terhadap stok yang tersedia.
- Keranjang pembelian dengan perhitungan subtotal dan total.
- Menyimpan transaksi dan detail transaksi secara atomik.
- Mengurangi stok produk dan menyimpan nama kasir serta nama pembeli.

### Pengembalian

- Memilih transaksi dan detail transaksi.
- Menampilkan informasi produk, jumlah, dan subtotal.
- Memproses pengembalian per detail transaksi.
- Mengembalikan stok produk.
- Menyesuaikan total transaksi.
- Menghapus transaksi secara otomatis jika seluruh detailnya sudah dikembalikan.

### Konsistensi Data

- Seluruh input SQL menggunakan `PreparedStatement`.
- Pembelian dan pengembalian menggunakan transaksi database.
- Jika salah satu operasi gagal, seluruh perubahan di-rollback.
- Foreign key menjaga relasi antara `transaksi`, `detail_transaksi`, `produk`, dan `kasir`.

## Prasyarat

- **JDK 11** (diuji dengan Temurin 11.0.14.1)
- **MySQL 8.4** melalui Laragon
- **NetBeans 12.6+** atau **Apache Ant 1.10+**
- Library berikut tersedia di `Main/lib`:
  - `mysql-connector-j-8.4.0.jar`
  - `commons-lang3-3.14.0.jar`
  - `jcalendar-1.4.jar`

Jika folder `Main/lib` kosong, unduh dependensi dari Maven Central:

- `https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar`
- `https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.14.0/commons-lang3-3.14.0.jar`
- `https://repo1.maven.org/maven2/com/toedter/jcalendar/1.4/jcalendar-1.4.jar`

> Catatan: `.gitignore` saat ini mengabaikan `*.jar`. Jika proyek di-clone, pastikan ketiga library di atas tersedia di `Main/lib`.

Jangan mengganti driver ke versi lama seperti Connector/J 5.1.x karena MySQL 8.4 menggunakan autentikasi `caching_sha2_password`.

## Setup

### 1. Siapkan Database

Aktifkan MySQL Laragon, lalu buat database dan impor data awal:

```bash
mysql -u root -e "CREATE DATABASE IF NOT EXISTS db_mart CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;"
mysql -u root db_mart --execute="source database/db_mart.sql"
```

Jika `mysql` belum ada di `PATH`, gunakan client bawaan Laragon atau impor `database/db_mart.sql` melalui phpMyAdmin.

Verifikasi hasil import:

```bash
mysql -u root db_mart --execute="SHOW TABLES; SELECT COUNT(*) AS jumlah_produk FROM produk;"
```

Hasil yang diharapkan:

- 4 tabel: `produk`, `kasir`, `transaksi`, `detail_transaksi`
- 10 baris `produk`
- 10 baris `kasir`
- 5 baris `transaksi`
- 24 baris `detail_transaksi`

> **Perhatian:** file `database/db_mart.sql` mengandung `DROP TABLE`. Impor hanya ke database kosong/uji, atau backup database lama terlebih dahulu.

### 2. Konfigurasi Koneksi

Konfigurasi utama berada di `Main/src/connection/DbConnection.java`.

Default koneksi:

```text
Host     : localhost
Port     : 3306
Database : db_mart
User     : root
Password : (kosong)
```

URL JDBC yang digunakan:

```text
jdbc:mysql://localhost:3306/db_mart?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Jakarta&characterEncoding=UTF-8
```

Nama database dapat diganti tanpa mengubah source code, misalnya untuk database uji:

```bash
cd Main
java -Datmart.db.name=db_mart_e2e -cp "build/classes;lib/*" view.MainView
```

### 3. Build dan Jalankan

#### Melalui NetBeans

1. Buka folder `Main` sebagai project NetBeans.
2. Klik **Clean and Build**.
3. Klik **Run** (F6). Main class sudah diset ke `view.MainView`.

#### Melalui Command Line

```bash
cd Main
ant compile
java -cp "build/classes;lib/*" view.MainView
```

Catatan:

- Pemisah classpath `;` digunakan di Windows. Di Linux/macOS gunakan `:`.
- Jika `ant` tidak tersedia di `PATH`, gunakan Ant bawaan NetBeans atau jalankan langsung dari IDE.
- Hasil build NetBeans tersedia di `Main/dist/E_7_Mart.jar`.

## Database dan Data Awal

Aplikasi tidak memiliki akun login. Data contoh yang tersedia setelah import:

| Komponen   | Nilai                                                     |
| ---------- | --------------------------------------------------------- |
| Database   | `db_mart`                                                 |
| Host       | `localhost`                                               |
| Port       | `3306`                                                    |
| User       | `root`                                                    |
| Password   | kosong                                                    |
| Nama kasir | Andi, Budi, Cici, Dedi, Eko, Fani, Gina, Hana, Iwan, Joko |

Gunakan konfigurasi ini hanya untuk local development/UAT. Untuk penggunaan di luar lingkungan lokal, buat user MySQL khusus dengan password kuat dan grant terbatas ke database `db_mart`.

## Menjalankan Aplikasi

1. Aktifkan MySQL Laragon.
2. Pastikan database `db_mart` sudah diimpor.
3. Jalankan aplikasi dari NetBeans atau command line.
4. Pilih menu **Produk**, **Kasir**, **Pembelian**, atau **Pengembalian** pada halaman utama.

## Pengujian

Pengujian E2E dilakukan pada database terpisah `db_mart_e2e` yang merupakan salinan `db_mart`, sehingga data produksi tidak tersentuh.

Cakupan pengujian:

- Koneksi, skema, engine InnoDB, dan foreign key.
- CRUD produk dan kasir.
- Penyimpanan karakter Unicode/emoji.
- Pencegahan SQL injection pada pencarian.
- Alur pembelian: total, stok, detail transaksi, dan relasi.
- Alur pengembalian: restore stok, perubahan total, dan penghapusan transaksi jika detail habis.
- Pemeriksaan kebocoran koneksi.
- Atomicity: pembelian gagal harus menghasilkan rollback tanpa data yatim.
- Inisialisasi seluruh panel UI dan start-up aplikasi GUI.

Untuk mengulang skenario pada database uji:

```bash
mysql -u root -e "DROP DATABASE IF EXISTS db_mart_e2e; CREATE DATABASE db_mart_e2e CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;"
mysql -u root db_mart_e2e --execute="source database/db_mart.sql"
cd Main
java -Datmart.db.name=db_mart_e2e -cp "build/classes;lib/*" view.MainView
```

Hapus `db_mart_e2e` setelah selesai agar tidak tertukar dengan database produksi.

### Hasil Pengujian Terakhir

Per 2026-11-05:

| Suite                                           | Hasil             |
| ----------------------------------------------- | ----------------- |
| Kompilasi 37 source file                        | PASS              |
| E2E database dan alur aplikasi (70 pemeriksaan) | 70/70 PASS        |
| Start-up aplikasi GUI                           | PASS              |
| Kebocoran koneksi setelah 10 pembacaan detail   | 0 koneksi tersisa |
| Checksum data produksi sebelum/sesudah E2E      | Identik           |
