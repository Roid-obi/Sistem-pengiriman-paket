import java.util.ArrayList;
import java.util.Scanner;

class Kurir {
    private int kurirId;
    private String namaKurir;

    public Kurir(int kurirId, String namaKurir) {
        this.kurirId = kurirId;
        this.namaKurir = namaKurir;
    }

    public int getKurirId() {
        return kurirId;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    @Override
    public String toString() {
        return "Kurir ID: " + kurirId + ", Nama: " + namaKurir;
    }
}

class Paket {
    private static int counterPaket = 1;
    private int paketId;
    private String nomorResi;
    private String namaPenerima;
    private String alamatPenerima;
    private String status;
    private Kurir kurir;

    public Paket(String nomorResi, String namaPenerima, String alamatPenerima) {
        this.paketId = counterPaket++;
        this.nomorResi = nomorResi;
        this.namaPenerima = namaPenerima;
        this.alamatPenerima = alamatPenerima;
        this.status = "Belum Dikirim";
        this.kurir = null;
    }

    public int getPaketId() {
        return paketId;
    }

    public String getNomorResi() {
        return nomorResi;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Kurir getKurir() {
        return kurir;
    }

    public void setKurir(Kurir kurir) {
        this.kurir = kurir;
    }

    @Override
    public String toString() {
        return "-------------------------------\n" +
                "| Paket ID : " + paketId + "\n" +
                "| No. Resi : " + nomorResi + "\n" +
                "| Nama Penerima : " + namaPenerima + "\n" +
                "| Alamat Penerima : " + alamatPenerima + "\n" +
                "| Status Pengiriman : " + status + "\n" +
                "| Kurir : " + (kurir != null ? kurir.getNamaKurir() : "-") + "\n" +
                "-------------------------------";
    }
}

public class ManajemenPaketKurir {
    private static ArrayList<Paket> daftarPaket = new ArrayList<>();
    private static ArrayList<Kurir> daftarKurir = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        daftarKurir.add(new Kurir(1, "JNE"));
        daftarKurir.add(new Kurir(2, "JNT"));
        daftarKurir.add(new Kurir(3, "FAST"));

        daftarPaket.add(new Paket("101010", "Ahmad", "Semarang"));
        daftarPaket.add(new Paket("202020", "Budi", "Jakarta"));
        daftarPaket.add(new Paket("303030", "Citra", "Surabaya"));
        daftarPaket.add(new Paket("101011", "Ahmad", "Solo"));
        daftarPaket.add(new Paket("212121", "joko", "semarang"));

        boolean keluar = false;

        while (!keluar) {
            tampilkanMenu();
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahPaket();
                    break;
                case 2:
                    hapusPaket();
                    break;
                case 3:
                    ubahStatusPaket();
                    break;
                case 4:
                    tampilkanSemuaPaket();
                    break;
                case 5:
                    pencarian();
                    break;
                case 6:
                    pengurutan();
                    break;
                case 7:
                    keluar = true;
                    System.out.println("Terima kasih telah Menggunakan Manajemen Paket");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi");
            }
        }

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n----- Sistem Manajemen Paket Kurir -----");
        System.out.println("1. Tambah Data Paket");
        System.out.println("2. Hapus Data Paket");
        System.out.println("3. Ubah Status Pengiriman Paket");
        System.out.println("4. Tampilkan Semua Data Paket");
        System.out.println("5. Pencarian Data Paket");
        System.out.println("6. Pengurutan Data Paket");
        System.out.println("7. Keluar");
        System.out.print("Masukkan pilihan Anda : ");
    }

    private static void tambahPaket() {
        System.out.print("Masukkan Nomor Resi : ");
        String nomorResi = scanner.nextLine();

        System.out.print("Masukkan Nama Penerima : ");
        String namaPenerima = scanner.nextLine();

        System.out.print("Masukkan Alamat Penerima : ");
        String alamatPenerima = scanner.nextLine();

        Paket paketBaru = new Paket(nomorResi, namaPenerima, alamatPenerima);
        daftarPaket.add(paketBaru);
        System.out.println("Paket berhasil ditambahkan!");
    }

    private static void hapusPaket() {
        if (daftarPaket.isEmpty()) {
            System.out.println("Tidak ada paket untuk dihapus");
            return;
        }

        System.out.print("Masukkan Nomor Resi paket yang akan dihapus : ");
        String nomorResi = scanner.nextLine();

        for (int i = 0; i < daftarPaket.size(); i++) {
            if (daftarPaket.get(i).getNomorResi().equals(nomorResi)) {
                daftarPaket.remove(i);
                System.out.println("Paket berhasil dihapus");
                return;
            }
        }
        System.out.println("Paket dengan nomor resi tersebut tidak ditemukan");
    }

    private static void tampilkanSemuaPaket() {
        if (daftarPaket.isEmpty()) {
            System.out.println("Tidak ada paket yang tersedia");
            return;
        }

        System.out.println("\n----- Daftar Paket -----");
        for (Paket paket : daftarPaket) {
            System.out.println(paket);
        }
    }

    private static void ubahStatusPaket() {
        if (daftarPaket.isEmpty()) {
            System.out.println("Tidak ada paket untuk diubah statusnya");
            return;
        }

        System.out.print("Masukkan Nomor Resi paket yang akan diubah statusnya : ");
        String nomorResi = scanner.nextLine();

        for (Paket paket : daftarPaket) {
            if (paket.getNomorResi().equals(nomorResi)) {
                System.out.println("Status saat ini : " + paket.getStatus());
                System.out.println("Pilih Status Baru :");
                System.out.println("1. Belum Dikirim");
                System.out.println("2. Sedang Dikirim");
                System.out.println("3. Sudah Diterima");
                System.out.print("Masukkan pilihan : ");

                int statusPilihan = scanner.nextInt();
                scanner.nextLine();

                switch (statusPilihan) {
                    case 1:
                        paket.setStatus("Belum Dikirim");
                        paket.setKurir(null);
                        break;
                    case 2:
                        paket.setStatus("Sedang Dikirim");
                        pilihKurir(paket);
                        break;
                    case 3:
                        paket.setStatus("Sudah Diterima");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        return;
                }

                System.out.println("Status paket berhasil diperbarui");
                return;
            }
        }

        System.out.println("Paket dengan nomor resi tersebut tidak ditemukan");
    }

    private static void pilihKurir(Paket paket) {
        System.out.println("Pilih Kurir:");
        for (Kurir kurir : daftarKurir) {
            System.out.println(kurir.getKurirId() + ". " + kurir.getNamaKurir());
        }
        System.out.print("Masukkan pilihan kurir : ");

        int kurirPilihan = scanner.nextInt();
        scanner.nextLine();

        for (Kurir kurir : daftarKurir) {
            if (kurir.getKurirId() == kurirPilihan) {
                paket.setKurir(kurir);
                return;
            }
        }
        System.out.println("Kurir tidak valid");
    }

    private static void pencarian() {
        if (daftarPaket.isEmpty()) {
            System.out.println("Tidak ada data untuk dicari");
            return;
        }

        System.out.println("Pilih kriteria pencarian : ");
        System.out.println("1. Paket berdasarkan Nomor Resi");
        System.out.println("2. Paket berdasarkan Nama Penerima");
        System.out.println("3. Paket berdasarkan Alamat Penerima");
        System.out.println("4. Paket berdasarkan Kurir");
        System.out.print("Masukkan pilihan : ");

        int pilihanCari = scanner.nextInt();
        scanner.nextLine();

        switch (pilihanCari) {
            case 1:
                System.out.print("Masukkan Nomor Resi : ");
                String nomorResi = scanner.nextLine();
                cariPaketByResi(nomorResi);
                break;
            case 2:
                System.out.print("Masukkan Nama Penerima : ");
                String namaPenerima = scanner.nextLine();
                cariPaketByNama(namaPenerima);
                break;
            case 3:
                System.out.print("Masukkan Alamat Penerima : ");
                String alamatPenerima = scanner.nextLine();
                cariPaketByAlamat(alamatPenerima);
                break;
            case 4:
                pilihKurirUntukPencarian();
                break;
            default:
                System.out.println("Pilihan tidak valid");
        }
    }

    private static void pilihKurirUntukPencarian() {
        System.out.println("Pilih Kurir:");
        for (Kurir kurir : daftarKurir) {
            System.out.println(kurir.getKurirId() + ". " + kurir.getNamaKurir());
        }
        System.out.print("Masukkan pilihan kurir : ");

        int kurirPilihan = scanner.nextInt();
        scanner.nextLine();

        for (Kurir kurir : daftarKurir) {
            if (kurir.getKurirId() == kurirPilihan) {
                cariPaketByKurir(kurir.getNamaKurir());
                return;
            }
        }
        System.out.println("Kurir tidak valid");
    }

    private static void cariPaketByResi(String nomorResi) {
        for (Paket paket : daftarPaket) {
            if (paket.getNomorResi().equals(nomorResi)) {
                System.out.println("Paket ditemukan :");
                System.out.println(paket);
                return;
            }
        }
        System.out.println("Paket dengan nomor resi tersebut tidak ditemukan");
    }

    private static void cariPaketByNama(String namaPenerima) {
        boolean ditemukan = false;
        for (Paket paket : daftarPaket) {
            if (paket.getNamaPenerima().toLowerCase().contains(namaPenerima.toLowerCase())) {
                System.out.println(paket);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada paket dengan nama penerima tersebut");
        }
    }

    private static void cariPaketByAlamat(String alamatPenerima) {
        boolean ditemukan = false;
        for (Paket paket : daftarPaket) {
            if (paket.getAlamatPenerima().toLowerCase().contains(alamatPenerima.toLowerCase())) {
                System.out.println(paket);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada paket dengan alamat tersebut");
        }
    }

    private static void cariPaketByKurir(String namaKurir) {
        boolean ditemukan = false;
        for (Paket paket : daftarPaket) {
            if (paket.getKurir() != null &&
                    paket.getKurir().getNamaKurir().toLowerCase().contains(namaKurir.toLowerCase())) {
                System.out.println(paket);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada paket yang dikirim oleh kurir tersebut");
        }
    }

    private static void pengurutan() {
        if (daftarPaket.isEmpty()) {
            System.out.println("Tidak ada data untuk diurutkan");
            return;
        }

        System.out.println("Pilih kriteria pengurutan :");
        System.out.println("1. Urut berdasarkan Paket ID (Ascending)");
        System.out.println("2. Urut berdasarkan Paket ID (Descending)");
        System.out.print("Masukkan pilihan : ");

        int pilihanUrut = scanner.nextInt();
        scanner.nextLine();

        switch (pilihanUrut) {
            case 1:
                urutPaketByIdAscending();
                break;
            case 2:
                urutPaketByIdDescending();
                break;
            default:
                System.out.println("Pilihan tidak valid");
        }
    }

    private static void urutPaketByIdAscending() {
        daftarPaket.sort((p1, p2) -> Integer.compare(p1.getPaketId(), p2.getPaketId()));
        System.out.println("Paket berhasil diurutkan berdasarkan Paket ID (Ascending)");
        tampilkanSemuaPaket();
    }

    private static void urutPaketByIdDescending() {
        daftarPaket.sort((p1, p2) -> Integer.compare(p2.getPaketId(), p1.getPaketId()));
        System.out.println("Paket berhasil diurutkan berdasarkan Paket ID (Descending)");
        tampilkanSemuaPaket();
    }

}