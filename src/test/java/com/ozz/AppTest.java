package com.ozz;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        BufferedReader buffer = getFileAsBuffer("input11.txt");
        App application;
        try {
            application = new App(buffer);
            Tree root = application.solve();
            SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
            ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
            FancyVisitor vis3 = new FancyVisitor();

            root.accept(vis1);
            root.accept(vis2);
            root.accept(vis3);

            int res1 = vis1.getResult();
            int res2 = vis2.getResult();
            int res3 = vis3.getResult();

            System.out.println(res1);
            System.out.println(res2);
            System.out.println(res3);
            assertTrue( true );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader getFileAsBuffer(String string) {
        File file = new File (getClass().getClassLoader().getResource(string).getFile());
        try {
            return new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
