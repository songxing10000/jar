package com.example.myapplication;

import java.util.Vector;

class Test {
    public static void main(String[] args) throws Exception {
         byte[] byteArray = {0x00, 0x01, 0x02, 0x03};
        a("d", 3, byteArray);
    }

    private static void a(final String s, final int n, final byte[] array) throws Exception {
        System.out.println("方法调用：" + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("参数：" + s);
        System.out.println("参数：" + n);
        System.out.println("参数：" + array);
    }

    public static final void logArray(Vector aArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aArray.size(); i++) {
            Object element = aArray.get(i);
            System.out.println("Element type: " + element.getClass().getSimpleName());

//            if (element instanceof Byte[]) {
//                Byte[] row = (Byte[]) element;
//                sb.append("Row ").append(i).append(": ");
//                for (int j = 0; j < row.length; j++) {
//                    sb.append(row[j]).append(", ");
//                }
//                sb.delete(sb.length() - 2, sb.length());  // 删除最后的逗号和空格
//                sb.append(System.lineSeparator());
//            }
        }
        System.out.println(sb.toString());
    }
}