package list.linkedlist;

import java.util.Scanner;

public class StandAloneList<K> {
	
	public static StandAloneList<Integer> firstNode = null;
	public static StandAloneList<Integer> lastNode = null;
	
	private K data;
	private StandAloneList<K> next;
	/**
	 * @param data the data to set
	 */
	public void setData(K data) {
		this.data = data;
	}
	/**
	 * @return the data
	 */
	public K getData() {
		return data;
	}
	/**
	 * @param next the syncNext to set
	 */
	public void setNext(StandAloneList<K> next) {
		this.next = next;
	}
	/**
	 * @return the syncNext
	 */
	public StandAloneList<K> getSyncNext() {
		return next;
	}
	
	public static void main(String[] args) {
		int nextInt = 0;
		do {
			System.out.println("-1:To Quit");
			System.out.println("1:Add element");
			System.out.println("2:Remove specific element");
			System.out.println("3:Remove element at first");
			System.out.println("4:Remove element at last");
			System.out.println("5:Print elements");
			System.out.println("6:Reverse elements");
			Scanner in = new Scanner(System.in);
			nextInt = in.nextInt();
			switch (nextInt) {
			case 1:
				addElement();
				break;
			case 2:
				removeSpecificElement();
				break;
			case 3:
				removeElementAtFirst();
				break;
			case 4:
				removeElementAtLast();
				break;
			case 5:
				printElements();
				break;
			case 6:
				reverseElements();
				break;
			default:
				break;
			}
			
		} while (nextInt != -1);
		System.out.println("Thank you");
	}
	
	private static void reverseElements() {
		
	}
	private static void printElements() {
		StandAloneList<Integer> itrNode = firstNode;
		System.out.println("The list is as follows ");
		while(itrNode != null) {
			System.out.print(itrNode.getData());
			System.out.print(" -> ");
			itrNode = itrNode.getSyncNext();
		}
		System.out.println();
	}
	private static void removeElementAtLast() {
		
	}
	private static void removeElementAtFirst() {
		
	}
	private static void removeSpecificElement() {
		System.out.println("Enter the element to remove");
		Scanner in = new Scanner(System.in);
		int nextInt = in.nextInt();
		StandAloneList<Integer> prevNode = null;
		StandAloneList<Integer> itrNode = firstNode;
		while(itrNode != null) {
			if(itrNode.getData().equals(nextInt)) {
				boolean isFirstNode = (itrNode == firstNode);
				boolean isLastNode = (itrNode == lastNode);
				if(isFirstNode || isLastNode) {
					if(isFirstNode) {
						firstNode = itrNode.getSyncNext();
					}
					if(isLastNode) {
						if(prevNode != null)
							prevNode.setNext(null);
						lastNode = prevNode;
					}
				} else {
					prevNode.setNext(itrNode.getSyncNext());
				}
			} else {
				prevNode = itrNode;
			}
			itrNode = itrNode.getSyncNext();
		}
	}
	private static void addElement() {
		System.out.println("Enter the element to add");
		Scanner in = new Scanner(System.in);
		int nextInt = in.nextInt();
		StandAloneList<Integer> newSyncNode = new StandAloneList<Integer>();
		newSyncNode.setData(nextInt);
		if(firstNode == null) {
			firstNode = newSyncNode;
			lastNode = newSyncNode;
			return;
		}
		StandAloneList<Integer> prevNode = null;
		StandAloneList<Integer> itrNode = firstNode;
		while(itrNode != null) {
			Integer data2 = itrNode.getData();
			if(data2 >= nextInt) {
				if(prevNode == null) {
					//newSyncNode will become head
					firstNode = newSyncNode;
					newSyncNode.setNext(itrNode);
				} else {
					prevNode.setNext(newSyncNode);
					newSyncNode.setNext(itrNode);
				}
				return;
			}
			prevNode = itrNode;
			itrNode = itrNode.getSyncNext();
		}
		lastNode.setNext(newSyncNode);
		lastNode = newSyncNode;
		
	}
}
