package com.willbehn.mtg;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.willbehn.mtg.model.MtgSet;
import com.willbehn.mtg.service.SetService;

@SpringBootTest
public class SetServiceTest {
    
    @Autowired
    SetService setService;

    @Test
    public void testGetCard() throws IOException, InterruptedException {
        MtgSet set = setService.getSet("clb");
        System.out.println(set);
    }
}
