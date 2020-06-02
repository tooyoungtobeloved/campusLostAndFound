package user.controller;

public class test {

public static void main(String[] args) {
	String reg = "^[\\w\\.]{6,16}$";
	String a = "111111";
	System.out.println(a.matches(reg));
}

}
