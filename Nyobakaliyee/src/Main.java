import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123";

    private static final String MAHASISWA_USERNAME = "dian";
    private static final String MAHASISWA_PASSWORD = "dian123";

    private static HashMap<String, Double> nilaiMahasiswa = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;
        String loggedInUser = "";
        boolean isAdmin = false;

        while (true) {
            if (!isLoggedIn) {
                System.out.println("\n==== Menu Login ====");
                System.out.println("1. Login sebagai Admin");
                System.out.println("2. Login sebagai Mahasiswa");
                System.out.println("3. Keluar");
                System.out.print("Masukkan pilihan: ");

                int pilihan = inputAngka(scanner);

                if (pilihan == 1) {
                    if (login(scanner, ADMIN_USERNAME, ADMIN_PASSWORD)) {
                        isLoggedIn = true;
                        loggedInUser = ADMIN_USERNAME;
                        isAdmin = true;
                    }
                } else if (pilihan == 2) {
                    if (login(scanner, MAHASISWA_USERNAME, MAHASISWA_PASSWORD)) {
                        isLoggedIn = true;
                        loggedInUser = MAHASISWA_USERNAME;
                        isAdmin = false;
                    }
                } else if (pilihan == 3) {
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;
                } else {
                    System.out.println("Pilihan tidak valid! Masukkan angka 1, 2, atau 3.");
                }
            } else {
                if (isAdmin) {
                    menuAdmin(scanner);
                } else {
                    menuMahasiswa(scanner, loggedInUser);
                }
                isLoggedIn = false; // Logout setelah kembali ke menu utama
            }
        }
        scanner.close();
    }

    private static boolean login(Scanner scanner, String validUsername, String validPassword) {
        System.out.print("Masukkan Username: ");
        String userName = scanner.nextLine();

        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        if (userName.equals(validUsername) && password.equals(validPassword)) {
            System.out.println("Login berhasil!");
            return true;
        } else {
            System.out.println("Login gagal! Username atau password salah.");
            return false;
        }
    }

    private static void menuAdmin(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Masukkan Nilai Mahasiswa");
            System.out.println("2. Logout");
            System.out.print("Masukkan pilihan: ");

            int pilihan = inputAngka(scanner);

            if (pilihan == 1) {
                masukkanNilai(scanner);
            } else if (pilihan == 2) {
                System.out.println("Logout berhasil! Kembali ke menu utama.");
                break;
            } else {
                System.out.println("Pilihan tidak valid! Silakan pilih 1 atau 2.");
            }
        }
    }

    private static void menuMahasiswa(Scanner scanner, String mahasiswa) {
        while (true) {
            System.out.println("\n=== Menu Mahasiswa ===");
            System.out.println("1. Lihat Jadwal Mata Kuliah");
            System.out.println("2. Lihat Nilai");
            System.out.println("3. Logout");
            System.out.print("Masukkan pilihan: ");

            int pilihan = inputAngka(scanner);

            if (pilihan == 1) {
                tampilkanJadwalMatakuliah();
            } else if (pilihan == 2) {
                tampilkanNilai(mahasiswa);
            } else if (pilihan == 3) {
                System.out.println("Logout berhasil! Kembali ke menu utama.");
                break;
            } else {
                System.out.println("Pilihan tidak valid! Silakan pilih 1, 2, atau 3.");
            }
        }
    }

    private static void masukkanNilai(Scanner scanner) {
        System.out.println("\n=== Masukkan Nilai Mahasiswa ===");
        System.out.print("Masukkan nama mahasiswa: ");
        String nama = scanner.nextLine();

        double nilaiAkhir = hitungNilai(scanner);
        if (nilaiAkhir >= 0) {
            nilaiMahasiswa.put(nama, nilaiAkhir);
            System.out.println("Nilai untuk " + nama + " telah disimpan.");
        }
    }

    private static double hitungNilai(Scanner scanner) {
        double tugas = 0, uts = 0, uas = 0;

        try {
            tugas = inputNilai(scanner, "tugas");
            uts = inputNilai(scanner, "UTS");
            uas = inputNilai(scanner, "UAS");

        } catch (InputMismatchException e) {
            System.out.println("Input nilai tidak valid! Masukkan angka antara 0 - 100.");
            scanner.nextLine(); // Bersihkan buffer
            return -1;
        }

        double finalScore = (tugas * 0.3) + (uts * 0.3) + (uas * 0.4);
        System.out.println("Nilai akhir: " + finalScore);
        return finalScore;
    }

    private static double inputNilai(Scanner scanner, String jenisNilai) {
        double nilai;
        while (true) {
            System.out.print("Masukkan nilai " + jenisNilai + " (0-100): ");
            nilai = scanner.nextDouble();
            scanner.nextLine(); // Konsumsi newline

            if (nilai >= 0 && nilai <= 100) {
                break;
            } else {
                System.out.println("Nilai harus dalam rentang 0 - 100!");
            }
        }
        return nilai;
    }

    private static void tampilkanJadwalMatakuliah() {
        System.out.println("\n=== Jadwal Mata Kuliah ===");
        System.out.println("1. Senin  - 08:00 - Matematika");
        System.out.println("2. Selasa - 10:00 - Pemrograman Java");
        System.out.println("3. Rabu   - 13:00 - Basis Data");
        System.out.println("4. Kamis  - 15:00 - Jaringan Komputer");
    }

    private static void tampilkanNilai(String mahasiswa) {
        System.out.println("\n=== Nilai Mahasiswa ===");
        if (nilaiMahasiswa.containsKey(mahasiswa)) {
            System.out.println("Nilai Anda: " + nilaiMahasiswa.get(mahasiswa));
        } else {
            System.out.println("Nilai belum dimasukkan oleh Admin.");
        }
    }

    private static int inputAngka(Scanner scanner) {
        int angka = 0;
        while (true) {
            try {
                angka = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline
                return angka;
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid! tolong masukan angka");
                scanner.nextLine(); // Bersihkan buffer
            }
        }
    }
}
