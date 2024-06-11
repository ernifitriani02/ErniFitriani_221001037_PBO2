/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crudapp;

/**
 *
 * @author user
 */
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/2210010370";
    static final String USER = "root"; // Ganti dengan username MySQL Anda
    static final String PASS = ""; // Ganti dengan password MySQL Anda

    static Connection conn = null;
    static Statement stmt = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            int pilihan;
do {
    tampilkanMenu();
    pilihan = scanner.nextInt();
    scanner.nextLine(); // Konsumsi newline
    switch (pilihan) {
        case 1:
            tambahKendaraan();
            break;
        case 2:
            tampilkanKendaraan();
            break;
        case 3:
            updateKendaraan();
            break;
        case 4:
            hapusKendaraan();
            break;
        case 5:
            tambahPaketPencucian();
            break;
        case 6:
            tampilkanPaketPencucian();
            break;
        case 7:
            updatePaketPencucian();
            break;
        case 8:
            hapusPaketPencucian();
            break;
        case 9:
            tambahPegawai();
            break;
        case 10:
            tampilkanPegawai();
            break;
        case 11:
            updatePegawai();
            break;
        case 12:
            hapusPegawai();
            break;
        case 13:
            tambahPelanggan();
            break;
        case 14:
            tampilkanPelanggan();
            break;
        case 15:
            updatePelanggan();
            break;
        case 16:
            hapusPelanggan();
            break;
        case 17:
            System.out.println("Keluar dari program...");
            break;
        default:
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
    }
} while (pilihan != 17);


            // Clean-up environment
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tampilkanMenu() {
    System.out.println("\n=== Menu Utama ===");
    System.out.println("1. Tambah Kendaraan");
    System.out.println("2. Tampilkan Daftar Kendaraan");
    System.out.println("3. Update Kendaraan");
    System.out.println("4. Hapus Kendaraan");
    System.out.println("5. Tambah Paket Pencucian");
    System.out.println("6. Tampilkan Daftar Paket Pencucian");
    System.out.println("7. Update Paket Pencucian");
    System.out.println("8. Hapus Paket Pencucian");
    System.out.println("9. Tambah Pegawai");
    System.out.println("10. Tampilkan Daftar Pegawai");
    System.out.println("11. Update Pegawai");
    System.out.println("12. Hapus Pegawai");
    System.out.println("13. Tambah Pelanggan");
    System.out.println("14. Tampilkan Daftar Pelanggan");
    System.out.println("15. Update Pelanggan");
    System.out.println("16. Hapus Pelanggan");
    System.out.println("17. Keluar");
    System.out.print("Pilih menu: ");
}


    public static void tambahKendaraan() {
        try {
            System.out.print("Masukkan jenis: ");
            String jenis = scanner.nextLine();
            System.out.print("Masukkan nama kendaraan: ");
            String nama_kendaraan = scanner.nextLine();
            System.out.print("Masukkan merk kendaraan: ");
            String merk_kendaraan = scanner.nextLine();

            String sql = "INSERT INTO data_kendaraan (jenis, nama_kendaraan, merk_kendaraan) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jenis);
            pstmt.setString(2, nama_kendaraan);
            pstmt.setString(3, merk_kendaraan);
            pstmt.executeUpdate();
            System.out.println("Data kendaraan berhasil ditambahkan.");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void tampilkanKendaraan() {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM data_kendaraan";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String jenis = rs.getString("jenis");
                String nama_kendaraan = rs.getString("nama_kendaraan");
                String merk_kendaraan = rs.getString("merk_kendaraan");
                System.out.print("ID: " + id);
                System.out.print(", Jenis: " + jenis);
                System.out.print(", Nama Kendaraan: " + nama_kendaraan);
                System.out.println(", Merk Kendaraan: " + merk_kendaraan);
            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void updateKendaraan() {
        try {
            System.out.print("Masukkan ID kendaraan yang akan diupdate: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            System.out.print("Masukkan jenis baru: ");
            String jenis = scanner.nextLine();
            System.out.print("Masukkan nama kendaraan baru: ");
            String nama_kendaraan = scanner.nextLine();
            System.out.print("Masukkan merk kendaraan baru: ");
            String merk_kendaraan = scanner.nextLine();

            String sql = "UPDATE data_kendaraan SET jenis = ?, nama_kendaraan = ?, merk_kendaraan = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jenis);
            pstmt.setString(2, nama_kendaraan);
            pstmt.setString(3, merk_kendaraan);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Data kendaraan berhasil diupdate.");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void hapusKendaraan() {
        try {
            System.out.print("Masukkan ID kendaraan yang akan dihapus: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            String sql = "DELETE FROM data_kendaraan WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Data kendaraan berhasil dihapus.");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public static void tambahPaketPencucian() {
    try {
        System.out.print("Masukkan jenis kendaraan: ");
        String jenis_kendaraan = scanner.nextLine();
        System.out.print("Masukkan jenis cuci: ");
        String jenis_cuci = scanner.nextLine();
        System.out.print("Masukkan harga cuci: ");
        double harga_cuci = scanner.nextDouble();
        scanner.nextLine(); // Konsumsi newline

        String sql = "INSERT INTO data_paket_pencucian (jenis_kendaraan, jenis_cuci, harga_cuci) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, jenis_kendaraan);
        pstmt.setString(2, jenis_cuci);
        pstmt.setDouble(3, harga_cuci);
        pstmt.executeUpdate();
        System.out.println("Data paket pencucian berhasil ditambahkan.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void tampilkanPaketPencucian() {
    try {
        stmt = conn.createStatement();
        String sql = "SELECT * FROM data_paket_pencucian";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String jenis_kendaraan = rs.getString("jenis_kendaraan");
            String jenis_cuci = rs.getString("jenis_cuci");
            double harga_cuci = rs.getDouble("harga_cuci");
            System.out.print("ID: " + id);
            System.out.print(", Jenis Kendaraan: " + jenis_kendaraan);
            System.out.print(", Jenis Cuci: " + jenis_cuci);
            System.out.println(", Harga Cuci: " + harga_cuci);
        }
        rs.close();
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void updatePaketPencucian() {
    try {
        System.out.print("Masukkan ID paket pencucian yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        System.out.print("Masukkan jenis kendaraan baru: ");
        String jenis_kendaraan = scanner.nextLine();
        System.out.print("Masukkan jenis cuci baru: ");
        String jenis_cuci = scanner.nextLine();
        System.out.print("Masukkan harga cuci baru: ");
        double harga_cuci = scanner.nextDouble();
        scanner.nextLine(); // Konsumsi newline

        String sql = "UPDATE data_paket_pencucian SET jenis_kendaraan = ?, jenis_cuci = ?, harga_cuci = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, jenis_kendaraan);
        pstmt.setString(2, jenis_cuci);
        pstmt.setDouble(3, harga_cuci);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
        System.out.println("Data paket pencucian berhasil diupdate.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void hapusPaketPencucian() {
    try {
        System.out.print("Masukkan ID paket pencucian yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String sql = "DELETE FROM data_paket_pencucian WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Data paket pencucian berhasil dihapus.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void tambahPegawai() {
    try {
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan jenis kelamin: ");
        String jenis_kelamin = scanner.nextLine();
        System.out.print("Masukkan tempat lahir: ");
        String tempat_lahir = scanner.nextLine();
        System.out.print("Masukkan alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan no HP: ");
        String no_hp = scanner.nextLine();
        System.out.print("Masukkan tanggal masuk (YYYY-MM-DD): ");
        String tanggal_masuk = scanner.nextLine();
        System.out.print("Masukkan gaji cuci mobil: ");
        double gaji_cuci_mobil = scanner.nextDouble();
        System.out.print("Masukkan gaji cuci motor: ");
        double gaji_cuci_motor = scanner.nextDouble();
        System.out.print("Masukkan total cuci motor: ");
        int total_cuci_motor = scanner.nextInt();
        System.out.print("Masukkan total cuci mobil: ");
        int total_cuci_mobil = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String sql = "INSERT INTO data_pegawai (nama, jenis_kelamin, tempat_lahir, alamat, no_hp, tanggal_masuk, gaji_cuci_mobil, gaji_cuci_motor, total_cuci_motor, total_cuci_mobil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nama);
        pstmt.setString(2, jenis_kelamin);
        pstmt.setString(3, tempat_lahir);
        pstmt.setString(4, alamat);
        pstmt.setString(5, no_hp);
        pstmt.setDate(6, Date.valueOf(tanggal_masuk));
        pstmt.setDouble(7, gaji_cuci_mobil);
        pstmt.setDouble(8, gaji_cuci_motor);
        pstmt.setInt(9, total_cuci_motor);
        pstmt.setInt(10, total_cuci_mobil);
        pstmt.executeUpdate();
        System.out.println("Data pegawai berhasil ditambahkan.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void tampilkanPegawai() {
    try {
        stmt = conn.createStatement();
        String sql = "SELECT * FROM data_pegawai";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nama = rs.getString("nama");
            String jenis_kelamin = rs.getString("jenis_kelamin");
            String tempat_lahir = rs.getString("tempat_lahir");
            String alamat = rs.getString("alamat");
            String no_hp = rs.getString("no_hp");
            Date tanggal_masuk = rs.getDate("tanggal_masuk");
            double gaji_cuci_mobil = rs.getDouble("gaji_cuci_mobil");
            double gaji_cuci_motor = rs.getDouble("gaji_cuci_motor");
            int total_cuci_motor = rs.getInt("total_cuci_motor");
            int total_cuci_mobil = rs.getInt("total_cuci_mobil");
            System.out.print("ID: " + id);
            System.out.print(", Nama: " + nama);
            System.out.print(", Jenis Kelamin: " + jenis_kelamin);
            System.out.print(", Tempat Lahir: " + tempat_lahir);
            System.out.print(", Alamat: " + alamat);
            System.out.print(", No HP: " + no_hp);
            System.out.print(", Tanggal Masuk: " + tanggal_masuk);
            System.out.print(", Gaji Cuci Mobil: " + gaji_cuci_mobil);
            System.out.print(", Gaji Cuci Motor: " + gaji_cuci_motor);
            System.out.print(", Total Cuci Motor: " + total_cuci_motor);
            System.out.println(", Total Cuci Mobil: " + total_cuci_mobil);
        }
        rs.close();
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void updatePegawai() {
    try {
        System.out.print("Masukkan ID pegawai yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        System.out.print("Masukkan nama baru: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan jenis kelamin baru: ");
        String jenis_kelamin = scanner.nextLine();
        System.out.print("Masukkan tempat lahir baru: ");
        String tempat_lahir = scanner.nextLine();
        System.out.print("Masukkan alamat baru: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan no HP baru: ");
        String no_hp = scanner.nextLine();
        System.out.print("Masukkan tanggal masuk baru (YYYY-MM-DD): ");
        String tanggal_masuk = scanner.nextLine();
        System.out.print("Masukkan gaji cuci mobil baru: ");
        double gaji_cuci_mobil = scanner.nextDouble();
        System.out.print("Masukkan gaji cuci motor baru: ");
        double gaji_cuci_motor = scanner.nextDouble();
        System.out.print("Masukkan total cuci motor baru: ");
        int total_cuci_motor = scanner.nextInt();
        System.out.print("Masukkan total cuci mobil baru: ");
        int total_cuci_mobil = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String sql = "UPDATE data_pegawai SET nama = ?, jenis_kelamin = ?, tempat_lahir = ?, alamat = ?, no_hp = ?, tanggal_masuk = ?, gaji_cuci_mobil = ?, gaji_cuci_motor = ?, total_cuci_motor = ?, total_cuci_mobil = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nama);
        pstmt.setString(2, jenis_kelamin);
        pstmt.setString(3, tempat_lahir);
        pstmt.setString(4, alamat);
        pstmt.setString(5, no_hp);
        pstmt.setDate(6, Date.valueOf(tanggal_masuk));
        pstmt.setDouble(7, gaji_cuci_mobil);
        pstmt.setDouble(8, gaji_cuci_motor);
        pstmt.setInt(9, total_cuci_motor);
        pstmt.setInt(10, total_cuci_mobil);
        pstmt.setInt(11, id);
        pstmt.executeUpdate();
        System.out.println("Data pegawai berhasil diupdate.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void hapusPegawai() {
    try {
        System.out.print("Masukkan ID pegawai yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String sql = "DELETE FROM data_pegawai WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Data pegawai berhasil dihapus.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}
public static void tambahPelanggan() {
    try {
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan no HP: ");
        String no_hp = scanner.nextLine();
        System.out.print("Masukkan total cuci mobil: ");
        int total_cuci_mobil = scanner.nextInt();
        System.out.print("Masukkan total cuci motor: ");
        int total_cuci_motor = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String sql = "INSERT INTO data_pelanggan (nama, no_hp, total_cuci_mobil, total_cuci_motor) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nama);
        pstmt.setString(2, no_hp);
        pstmt.setInt(3, total_cuci_mobil);
        pstmt.setInt(4, total_cuci_motor);
        pstmt.executeUpdate();
        System.out.println("Data pelanggan berhasil ditambahkan.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void tampilkanPelanggan() {
    try {
        stmt = conn.createStatement();
        String sql = "SELECT * FROM data_pelanggan";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nama = rs.getString("nama");
            String no_hp = rs.getString("no_hp");
            int total_cuci_mobil = rs.getInt("total_cuci_mobil");
            int total_cuci_motor = rs.getInt("total_cuci_motor");
            System.out.print("ID: " + id);
            System.out.print(", Nama: " + nama);
            System.out.print(", No HP: " + no_hp);
            System.out.print(", Total Cuci Mobil: " + total_cuci_mobil);
            System.out.println(", Total Cuci Motor: " + total_cuci_motor);
        }
        rs.close();
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void updatePelanggan() {
    try {
        System.out.print("Masukkan ID pelanggan yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        System.out.print("Masukkan nama baru: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan no HP baru: ");
        String no_hp = scanner.nextLine();
        System.out.print("Masukkan total cuci mobil baru: ");
        int total_cuci_mobil = scanner.nextInt();
        System.out.print("Masukkan total cuci motor baru: ");
        int total_cuci_motor = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String sql = "UPDATE data_pelanggan SET nama = ?, no_hp = ?, total_cuci_mobil = ?, total_cuci_motor = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nama);
        pstmt.setString(2, no_hp);
        pstmt.setInt(3, total_cuci_mobil);
        pstmt.setInt(4, total_cuci_motor);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
        System.out.println("Data pelanggan berhasil diupdate.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

public static void hapusPelanggan() {
    try {
        System.out.print("Masukkan ID pelanggan yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String sql = "DELETE FROM data_pelanggan WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Data pelanggan berhasil dihapus.");
    } catch (SQLException se) {
        se.printStackTrace();
    }
}

}

