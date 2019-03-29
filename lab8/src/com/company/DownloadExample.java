package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;



public class DownloadExample {
    static AtomicInteger counter = new AtomicInteger();
    static Semaphore semafor = new Semaphore(0);

    static class Downloader implements Runnable{
        private final String url;
        // lista plików do pobrania
        static String [] toDownload = {
                "http://home.agh.edu.pl/pszwed/wyklad-c/01-jezyk-c-intro.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
                "http://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
        };

        Downloader(String url){
                this.url = url;
            }
            public void run(){
                String fileName = url.substring(40);

                try(InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(fileName) ){
                    int a;
                    for(;;){
                        a = in.read();
                        if(a< 0)
                        {
                            break;
                        }
                        out.write(a);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(fileName);
                counter.getAndIncrement();
                semafor.release();
            }

        }

        private static void sequentialDownload(){
            double t1 = System.nanoTime()/1e9;
            for(String url: Downloader.toDownload){
                new Downloader(url).run();
            }
            double t2 = System.nanoTime()/1e9;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
        }
        static void concurrentDownload() throws InterruptedException {
            double t1 = System.nanoTime()/1e9;
            for(String url: Downloader.toDownload){
                new Thread(new Downloader(url)).start();
            }

            double t2 = System.nanoTime()/1e9;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
        }

        static void concurrentDownload2() throws InterruptedException {
            double t1 = System.nanoTime()/1e9;
            for(String url: Downloader.toDownload){
                new Thread(new Downloader(url)).start();
            }
            while(counter.intValue()!=Downloader.toDownload.length){
                Thread.yield();
            }

            double t2 = System.nanoTime()/1e9;
            System.out.printf(Locale.US,"t2-t1=%.2f\n",(t2-t1));
        }

        static void concurrentDownload3() throws InterruptedException {
            double t1 = System.nanoTime()/1e9;
            for(String url: Downloader.toDownload){
                new Thread(new Downloader(url)).start();
            }
            semafor.acquire(Downloader.toDownload.length);
            double t2 = System.nanoTime()/1e9;
            System.out.printf(Locale.US,"t2-t1=%.2f\n",(t2-t1));
        }


        public static void main(String[] args) throws InterruptedException {
            //concurrentDownload();             podawany niepoprawny czas gdyz nie czekamy na zakonczenie watku
            //concurrentDownload2();
            concurrentDownload3();
            //sequentialDownload();
        }

    }

