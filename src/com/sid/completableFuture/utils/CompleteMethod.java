package com.sid.completableFuture.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompleteMethod {

	public static void main(String[] args) {

		String s = "a";
		String q = "a";
		String w = new String("a");
		String w1 = new String("a");

		System.out.println(s == q);
		System.out.println(w1 == w);

		CompletableFuture<String> completableFuture = completeManually();
		try {
			String result = completableFuture.get();
			System.out.println(result);
			System.out.println(Thread.currentThread().getName());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static CompletableFuture<String> completeManually() {

		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		List<String> name = new ArrayList<>();
		name.add("Siddharth");
		name.add("Nawani");

		Executors.newCachedThreadPool().submit(() -> {
			try {
				Thread.sleep(100);
				String joined = String.join(",", name);
				System.out.println(Thread.currentThread().getName());
				completableFuture.complete(joined);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});

		return completableFuture;

	}

}
