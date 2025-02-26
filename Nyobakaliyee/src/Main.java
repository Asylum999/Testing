import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String admin = "admin";
        String adminPassword = "123";

        String mahasiswa = "dian";
        String mahasiswaPassword = "dian123";

        while (true) {
            System.out.println("\n==== Menu Login ====");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");

            int pilihan = 0;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid! Silakan masukkan angka 1, 2, atau 3.");
                scanner.nextLine();
                continue;
            }

            if (pilihan == 3) {
                System.out.println("Terima kasih, selamat tinggal.");
                scanner.close();
                System.exit(0);
            }

            System.out.print("Masukkan Username: ");
            String userName = scanner.nextLine();

            System.out.print("Masukkan Password: ");
            String password = scanner.nextLine();

            if (pilihan == 1) {
                if (userName.equals(admin) && password.equals(adminPassword)) {
                    System.out.println("Anda berhasil masuk sebagai Admin.");
                    hitungNilai(scanner);
                } else {
                    System.out.println("Login gagal! Username atau password salah.");
                }
            } else if (pilihan == 2) {
                if (userName.equals(mahasiswa) && password.equals(mahasiswaPassword)) {
                    System.out.println("Anda berhasil masuk sebagai Mahasiswa.");
                    hitungNilai(scanner);
                } else {
                    System.out.println("Login gagal! Username atau password salah.");
                }
            } else {
                System.out.println("Pilihan tidak valid! Silakan pilih 1, 2, atau 3.");
            }
        }
    }

    private static void hitungNilai(Scanner scanner) {
        System.out.println("\n=== Penghitung Nilai ===");
        double tugas = 0, uts = 0, uas = 0;

        try {
            System.out.print("Masukkan nilai tugas: ");
            tugas = scanner.nextDouble();
            System.out.print("Masukkan nilai UTS: ");
            uts = scanner.nextDouble();
            System.out.print("Masukkan nilai UAS: ");
            uas = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Input nilai tidak valid! Masukkan angka desimal.");
            scanner.nextLine();
            return;
        }

        double finalScore = (tugas * 0.3) + (uts * 0.3) + (uas * 0.4);
        System.out.println("Nilai akhir: " + finalScore);
    }
}
