/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;
/**
 *
 * @author UTKARSHA
 */
import java.util.*;
import java.sql.*;



class Apriori {
	static Set<Tuple> c;
	static Set<Tuple> l;
        static Set<Tuple> cc;
        static int b=1;
	static int d[][];
	static int min_support;
	
	public static void main(String args[]) throws Exception {
		getDatabase();
		c = new HashSet<>();
		l = new HashSet<>();
		Scanner scan = new Scanner(System.in);
		int i, j, m, n;
		System.out.println("Enter the minimum support (as an integer value):");
		min_support = scan.nextInt();
		Set<Integer> candidate_set = new HashSet<>();
                System.out.println("Dataset for mining association rule is:");
                System.out.print("TID  | Item_ID ");
                System.out.println();
		for(i=0 ; i < d.length ; i++) {
			System.out.print((i+1)+"    |");
			for(j=0 ; j < d[i].length ; j++) {
				System.out.print(d[i][j]+" ");
				candidate_set.add(d[i][j]);
			}
                        System.out.println();
		}
		
		Iterator<Integer> iterator = candidate_set.iterator();
		while(iterator.hasNext()) {
			Set<Integer> s = new HashSet<>();
			s.add(iterator.next());
			Tuple t = new Tuple(s, count(s));
			c.add(t);
		}
		
		prune();
		generateFrequentItemsets();
	}
	
	static int count(Set<Integer> s) {
		int i, j, k;
		int support = 0;
		int count;
		boolean containsElement;
		for(i=0 ; i < d.length ; i++) {
			count = 0;
			Iterator<Integer> iterator = s.iterator();
			while(iterator.hasNext()) {
				int element = iterator.next();
				containsElement = false;
				for(k=0 ; k < d[i].length ; k++) {
					if(element == d[i][k]) {
						containsElement = true;
						count++;
						break;
					}
				}
				if(!containsElement) {
					break;
				}
			}
			if(count == s.size()) {
				support++;
			}
		}
		return support;
	}
	
	static void prune() {
		l.clear();
		Iterator<Tuple> iterator = c.iterator();
                System.out.println("Before pruning "+b+"-itemset:");
		while(iterator.hasNext()) {
			Tuple t = iterator.next();
                        System.out.println(t.itemset+" : "+t.support);
			if(t.support >= min_support) {
				l.add(t);
			}
		}
		System.out.println("After pruning "+b+"-itemset:");
                if(!l.isEmpty())
		for(Tuple t : l) {
			System.out.println(t.itemset + " : " + t.support);
		}
                else
                    System.out.println("No more frequent sets!!");
                b++;
	}
	
	static void generateFrequentItemsets() {
		boolean toBeContinued = true;
		int element = 0;
		int size = 1;
		Set<Set> candidate_set = new HashSet<>();
		while(toBeContinued) {
			candidate_set.clear();
			c.clear();
			Iterator<Tuple> iterator = l.iterator();
			while(iterator.hasNext()) {
				Tuple t1 = iterator.next();
				Set<Integer> temp = t1.itemset;
				Iterator<Tuple> it2 = l.iterator();
				while(it2.hasNext()) {
					Tuple t2 = it2.next();
					Iterator<Integer> it3 = t2.itemset.iterator();
					while(it3.hasNext()) {
						try {
							element = it3.next();
						} catch(ConcurrentModificationException e) {
						          System.out.println(e);
						}
						temp.add(element);
						if(temp.size() != size) {
							Integer[] int_arr = temp.toArray(new Integer[0]);
							Set<Integer> temp2 = new HashSet<>();
							for(Integer x : int_arr) {
								temp2.add(x);
							}
							candidate_set.add(temp2);
							temp.remove(element);
						}
					}
				}
			}
			Iterator<Set> candidate_set_iterator = candidate_set.iterator();
			while(candidate_set_iterator.hasNext()) {
				Set s = candidate_set_iterator.next();
				c.add(new Tuple(s, count(s)));
			}
			prune();
			if(l.size() <= 1) {
				toBeContinued = false;
			}
			size++;
		}
		
	}
	
	static void getDatabase() throws Exception {
		
		Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/apriori");
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery("SELECT * FROM apriori");
		Map<Integer, List <Integer>> m = new HashMap<>();
		List<Integer> temp;
		while(rs.next()) {
			int list_no = Integer.parseInt(rs.getString(1));
			int object = Integer.parseInt(rs.getString(2));
			temp = m.get(list_no);
			if(temp == null) {
				temp = new LinkedList<>();
			}
			temp.add(object);
			m.put(list_no, temp);
		}
		
		Set<Integer> keyset = m.keySet();
		d = new int[keyset.size()][];
		Iterator<Integer> iterator = keyset.iterator();
		int count = 0;
		while(iterator.hasNext()) {
			temp = m.get(iterator.next());
			Integer[] int_arr = temp.toArray(new Integer[0]);
			d[count] = new int[int_arr.length];
			for(int i=0 ; i < d[count].length ; i++) {
				d[count][i] = int_arr[i].intValue();
			}
			count++;
		}
	}
}


class Tuple {
	Set<Integer> itemset;
	int support;
	
	Tuple() {
		itemset = new HashSet<>();
		support = -1;
	}
	
	Tuple(Set<Integer> s) {
		itemset = s;
		support = -1;
	}
	
	Tuple(Set<Integer> s, int i) {
		itemset = s;
		support = i;
	}
}