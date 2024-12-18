import java.util.ArrayList;
import java.util.Scanner;

// Kelas PaketBarang untuk menyimpan data paket yang dikirim
class PaketBarang {
    String idPaket;
    String namaPengirim;
    String namaPenerima;
    String alamatPenerima;
    String statusPengantaran;

    public PaketBarang(String idPaket, String namaPengirim, String namaPenerima, String alamatPenerima, String statusPengantaran) {
        this.idPaket = idPaket;
        this.namaPengirim = namaPengirim;
        this.namaPenerima = namaPenerima;
        this.alamatPenerima = alamatPenerima;
        this.statusPengantaran = statusPengantaran;
    }

    // Fungsi untuk mengubah status pengantaran paket
    public void updateStatus(String status) {
        this.statusPengantaran = status;
    }
}

// Kelas Sistem Paket Pengantaran
public class SistemPaketPengantaran {
    static ArrayList<PaketBarang> daftarPaket = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
                case 0:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 0);
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
        System.out.println("| 0. Keluar                              |");
        System.out.println("+========================================+");
    }

    // Fungsi untuk menambah paket baru
    public static void tambahPaket() {
        System.out.print("Masukkan ID Paket: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Pengirim: ");
        String pengirim = scanner.nextLine();
        System.out.print("Masukkan Nama Penerima: ");
        String penerima = scanner.nextLine();
        System.out.print("Masukkan Alamat Penerima: ");
        String alamat = scanner.nextLine();

        String status = pilihStatusPengantaran();

        PaketBarang paket = new PaketBarang(id, pengirim, penerima, alamat, status);
        daftarPaket.add(paket);
        System.out.println("\nPaket berhasil ditambahkan.");
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

    // Fungsi untuk menghapus paket berdasarkan ID
    public static void hapusPaket() {
        System.out.print("Masukkan ID Paket yang ingin dihapus: ");
        String id = scanner.nextLine();
        boolean ditemukan = false;

        for (PaketBarang paket : daftarPaket) {
            if (paket.idPaket.equals(id)) {
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
            System.out.println("\n+=========================================================================================+");
            System.out.println("| ID Paket  | Pengirim         | Penerima         | Alamat Penerima       | Status        |");
            System.out.println("+=========================================================================================+");
            for (PaketBarang paket : daftarPaket) {
                System.out.printf("| %-10s| %-17s| %-17s| %-22s| %-14s|%n",
                        paket.idPaket, paket.namaPengirim, paket.namaPenerima, paket.alamatPenerima, paket.statusPengantaran);
            }
            System.out.println("+=========================================================================================+");
        }
    }

    // Fungsi untuk memperbarui status paket berdasarkan ID
    public static void updateStatusPaket() {
        System.out.print("Masukkan ID Paket yang ingin diubah statusnya: ");
        String id = scanner.nextLine();
        boolean ditemukan = false;

        for (PaketBarang paket : daftarPaket) {
            if (paket.idPaket.equals(id)) {
                System.out.println("\nPilih Status Pengantaran yang baru:");
                String statusBaru = pilihStatusPengantaran();
                paket.updateStatus(statusBaru);
                System.out.println("\nStatus paket dengan ID " + id + " berhasil diperbarui menjadi " + statusBaru + ".");
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("\nPaket dengan ID " + id + " tidak ditemukan.");
        }
    }
}
