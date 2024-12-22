import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Kelas PaketBarang untuk menyimpan data paket yang dikirim
class PaketBarang {
    int idPaket;
    String nomorResi;
    String namaPengirim;
    String namaPenerima;
    String alamatPenerima;
    String statusPengantaran;
    String kurir; // Tambahkan atribut kurir

    public PaketBarang(int idPaket, String nomorResi, String namaPengirim, String namaPenerima, String alamatPenerima, String statusPengantaran, String kurir) {
        this.idPaket = idPaket;
        this.nomorResi = nomorResi;
        this.namaPengirim = namaPengirim;
        this.namaPenerima = namaPenerima;
        this.alamatPenerima = alamatPenerima;
        this.statusPengantaran = statusPengantaran;
        this.kurir = kurir; // Inisialisasi atribut kurir
    }

    public void updateStatus(String status) {
        this.statusPengantaran = status;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }
}


// Kelas Sistem Paket Pengantaran
public class SistemPaketPengantaran {
    static ArrayList<PaketBarang> daftarPaket = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        // Menambahkan paket dummy saat program dimulai
        dataPaket();

        int pilihan;
        do {
            tampilkanUI();
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    tambahPaket();
                    break;
                case 2:
                    hapusPaket();
                    break;
                case 3:
                    tampilSemuaPaket();
                    break;
                case 4:
                    updateStatusPaket();
                    break;
                case 5:
                    cariPaket();
                    break;
                case 6:
                    urutkanPaket();
                    break;
                case 0:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 0);
    }

    // Data dummy paket
    public static void dataPaket() {
        daftarPaket.add(new PaketBarang(idCounter++, generateNomorResi(), "Kurumi", "Alice", "Jl. Mawar No. 1", "Pending", "-"));
        daftarPaket.add(new PaketBarang(idCounter++, generateNomorResi(), "Must a nine", "Bob", "Jl. Melati No. 2", "On Delivery", "J&T Express"));
        daftarPaket.add(new PaketBarang(idCounter++, generateNomorResi(), "Sigma Boy", "Charlie", "Jl. Anggrek No. 3", "Delivered", "-"));
    }

    // Menampilkan antarmuka pengguna (UI) untuk memilih menu
    public static void tampilkanUI() {
        System.out.println("+========================================+");
        System.out.println("|         Sistem Paket Pengantaran       |");
        System.out.println("+========================================+");
        System.out.println("| 1. Tambah Paket                        |");
        System.out.println("| 2. Hapus Paket                         |");
        System.out.println("| 3. Tampilkan Semua Paket               |");
        System.out.println("| 4. Update Status Paket                 |");
        System.out.println("| 5. Cari Paket                          |");
        System.out.println("| 6. Urutkan Paket berdasarkan ID Paket  |");
        System.out.println("| 0. Keluar                              |");
        System.out.println("+========================================+");
    }

    // Fungsi untuk menambah paket baru
    public static void tambahPaket() {
        System.out.print("Masukkan Nama Pengirim: ");
        String pengirim = scanner.nextLine();
        System.out.print("Masukkan Nama Penerima: ");
        String penerima = scanner.nextLine();
        System.out.print("Masukkan Alamat Penerima: ");
        String alamat = scanner.nextLine();

        // Status otomatis diatur menjadi "Pending"
        String status = "Pending";
        String nomorResi = generateNomorResi();
        String kurir = "-";

        PaketBarang paket = new PaketBarang(idCounter++, nomorResi, pengirim, penerima, alamat, status, kurir);
        daftarPaket.add(paket);
        System.out.println("\nPaket berhasil ditambahkan dengan nomor resi " + nomorResi + ".");
    }

    // Fungsi untuk menghasilkan nomor resi unik
    public static String generateNomorResi() {
        return "RESI" + System.currentTimeMillis();
    }

    // Fungsi untuk menghapus paket berdasarkan ID
    public static void hapusPaket() {
        System.out.print("Masukkan ID Paket yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        boolean ditemukan = false;

        for (PaketBarang paket : daftarPaket) {
            if (paket.idPaket == id) {
                daftarPaket.remove(paket);
                System.out.println("\nPaket dengan ID " + id + " berhasil dihapus.");
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("\nPaket dengan ID " + id + " tidak ditemukan.");
        }
    }

    // Fungsi untuk menampilkan semua paket yang ada
    public static void tampilSemuaPaket() {
        if (daftarPaket.isEmpty()) {
            System.out.println("\nTidak ada data paket.");
        } else {
            System.out.println("\n+===========================================================================================================================+");
            System.out.println("| ID Paket | Nomor Resi        | Pengirim         | Penerima         | Alamat Penerima       | Status        | Kurir        |");
            System.out.println("+===========================================================================================================================+");
            for (PaketBarang paket : daftarPaket) {
                System.out.printf("| %-9d| %-18s| %-17s| %-17s| %-22s| %-14s| %-13s|%n",
                        paket.idPaket, paket.nomorResi, paket.namaPengirim, paket.namaPenerima, paket.alamatPenerima, paket.statusPengantaran, paket.kurir);
            }
            System.out.println("+===========================================================================================================================+");
        }
    }

    // Fungsi untuk memperbarui status paket berdasarkan ID
    public static void updateStatusPaket() {
        System.out.print("Masukkan ID Paket yang ingin diubah statusnya: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        boolean ditemukan = false;
    
        for (PaketBarang paket : daftarPaket) {
            if (paket.idPaket == id) {
                System.out.println("\nPilih Status Pengantaran yang baru:");
                String statusBaru = pilihStatusPengantaran();
                paket.updateStatus(statusBaru);
    
                if (statusBaru.equals("On Delivery")) {
                    System.out.println("\nPilih Kurir:");
                    String kurirBaru = pilihKurir();
                    paket.setKurir(kurirBaru);
                } else {
                    paket.setKurir("-"); // Kurir diatur ke "-" jika bukan On Delivery
                }
    
                System.out.println("\nStatus paket dengan ID " + id + " berhasil diperbarui menjadi " + statusBaru + ".");
                ditemukan = true;
                break;
            }
        }
    
        if (!ditemukan) {
            System.out.println("\nPaket dengan ID " + id + " tidak ditemukan.");
        }
    }

    public static String pilihKurir() {
        System.out.println("1. JNE Express");
        System.out.println("2. J&T Express");
        System.out.println("3. Ninja Xpress");
        System.out.print("Pilihan: ");
    
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
    
        switch (pilihan) {
            case 1:
                return "JNE Express";
            case 2:
                return "J&T Express";
            case 3:
                return "Ninja Xpress";
            default:
                System.out.println("Pilihan tidak valid. Kurir diatur ke 'JNE Express'.");
                return "JNE Express";
        }
    }    


    // Fungsi untuk memilih status pengantaran paket
    public static String pilihStatusPengantaran() {
        System.out.println("Pilih Status Pengantaran:");
        System.out.println("1. Pending");
        System.out.println("2. On Delivery");
        System.out.println("3. Delivered");
        System.out.print("Pilihan: ");

        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (pilihan) {
            case 1:
                return "Pending";
            case 2:
                return "On Delivery";
            case 3:
                return "Delivered";
            default:
                System.out.println("Pilihan tidak valid. Status diatur ke 'Pending'.");
                return "Pending";
        }
    }

    // PENCARIAN DATA

    // Fungsi untuk mencari paket berdasarkan berbagai kriteria
    public static void cariPaket() {
        System.out.println("Ingin mencari berdasarkan apa?");
        System.out.println("1. ID Paket");
        System.out.println("2. Nomor Resi");
        System.out.println("3. Nama Pengirim");
        System.out.println("4. Nama Penerima");
        System.out.println("5. Alamat Penerima");
        System.out.println("6. Status Pengantaran");
        System.out.println("7. Kurir");
        System.out.print("Pilihan: ");

        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (pilihan) {
            case 1:
                System.out.print("Masukkan ID Paket: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                cariPaketById(id);
                break;
            case 2:
                System.out.print("Masukkan Nomor Resi: ");
                String resi = scanner.nextLine();
                cariPaketByNomorResi(resi);
                break;
            case 3:
                System.out.print("Masukkan Nama Pengirim: ");
                String pengirim = scanner.nextLine();
                cariPaketByPengirim(pengirim);
                break;
            case 4:
                System.out.print("Masukkan Nama Penerima: ");
                String penerima = scanner.nextLine();
                cariPaketByPenerima(penerima);
                break;
            case 5:
                System.out.print("Masukkan Alamat Penerima: ");
                String alamat = scanner.nextLine();
                cariPaketByAlamat(alamat);
                break;
            case 6:
                String status = pilihStatusPengantaran();
                cariPaketByStatus(status);
                break;
            case 7:
                String kurir = pilihKurir();
                cariPaketByKurir(kurir);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    // Fungsi untuk mencari berdasarkan ID Paket
    public static void cariPaketById(int id) {
        boolean ditemukan = false;
        for (PaketBarang paket : daftarPaket) {
            if (String.valueOf(paket.idPaket).startsWith(String.valueOf(id))) {
                tampilkanDetailPaket(paket);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("\nPaket dengan ID " + id + " tidak ditemukan.");
        }
    }

    // Fungsi untuk mencari berdasarkan Nomor Resi
    public static void cariPaketByNomorResi(String resi) {
        boolean ditemukan = false;
        for (PaketBarang paket : daftarPaket) {
            if (paket.nomorResi.startsWith(resi)) {
                tampilkanDetailPaket(paket);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("\nPaket dengan Nomor Resi " + resi + " tidak ditemukan.");
        }
    }

    // Fungsi untuk mencari berdasarkan Nama Pengirim
    public static void cariPaketByPengirim(String pengirim) {
        boolean ditemukan = false;
        for (PaketBarang paket : daftarPaket) {
            if (paket.namaPengirim.toLowerCase().startsWith(pengirim.toLowerCase())) {
                tampilkanDetailPaket(paket);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("\nPaket dengan Nama Pengirim " + pengirim + " tidak ditemukan.");
        }
    }

    // Fungsi untuk mencari berdasarkan Nama Penerima
    public static void cariPaketByPenerima(String penerima) {
        boolean ditemukan = false;
        for (PaketBarang paket : daftarPaket) {
            if (paket.namaPenerima.toLowerCase().startsWith(penerima.toLowerCase())) {
                tampilkanDetailPaket(paket);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("\nPaket dengan Nama Penerima " + penerima + " tidak ditemukan.");
        }
    }

    // Fungsi untuk mencari berdasarkan Alamat Penerima
    public static void cariPaketByAlamat(String alamat) {
        boolean ditemukan = false;
        for (PaketBarang paket : daftarPaket) {
            if (paket.alamatPenerima.toLowerCase().startsWith(alamat.toLowerCase())) {
                tampilkanDetailPaket(paket);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("\nPaket dengan Alamat Penerima " + alamat + " tidak ditemukan.");
        }
    }

    // Fungsi untuk mencari berdasarkan Status Pengantaran
    public static void cariPaketByStatus(String status) {
        boolean ditemukan = false;
        for (PaketBarang paket : daftarPaket) {
            if (paket.statusPengantaran.equalsIgnoreCase(status)) {
                tampilkanDetailPaket(paket);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("\nTidak ada paket dengan status pengantaran: " + status);
        }
    }

    // Fungsi untuk mencari berdasarkan Kurir
    public static void cariPaketByKurir(String kurir) {
        boolean ditemukan = false;
        for (PaketBarang paket : daftarPaket) {
            if (paket.kurir.equalsIgnoreCase(kurir)) {
                tampilkanDetailPaket(paket);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("\nTidak ada paket dengan kurir: " + kurir);
        }
    }

    // Fungsi untuk menampilkan detail paket dalam bentuk tabel
    public static void tampilkanDetailPaket(PaketBarang paket) {
        System.out.println("\n+===========================================================================================================================+");
        System.out.println("| ID Paket | Nomor Resi        | Pengirim         | Penerima         | Alamat Penerima       | Status        | Kurir        |");
        System.out.println("+===========================================================================================================================+");
        System.out.printf("| %-9d| %-18s| %-17s| %-17s| %-22s| %-14s| %-13s|%n",
                paket.idPaket, paket.nomorResi, paket.namaPengirim, paket.namaPenerima, paket.alamatPenerima, paket.statusPengantaran, paket.kurir);
        System.out.println("+===========================================================================================================================+");
    }

    // Fungsi untuk mengurutkan paket berdasarkan ID
    public static void urutkanPaket() {
        System.out.print("Urutkan berdasarkan ID Paket (1: Kecil ke Besar, 2: Besar ke Kecil): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        if (pilihan == 1) {
            // Urutkan kecil ke besar
            Collections.sort(daftarPaket, Comparator.comparingInt(paket -> paket.idPaket));
            System.out.println("\nData paket diurutkan dari ID terkecil ke terbesar.");
        } else if (pilihan == 2) {
            // Urutkan besar ke kecil
            Collections.sort(daftarPaket, (p1, p2) -> Integer.compare(p2.idPaket, p1.idPaket));
            System.out.println("\nData paket diurutkan dari ID terbesar ke terkecil.");
        } else {
            System.out.println("\nPilihan tidak valid.");
            return;
        }
        tampilSemuaPaket();
    }

}
