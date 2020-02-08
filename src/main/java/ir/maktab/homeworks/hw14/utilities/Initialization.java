package ir.maktab.homeworks.hw14.utilities;

import ir.maktab.homeworks.hw14.entities.BankBranch;
import ir.maktab.homeworks.hw14.repositories.BankBranchRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Initialization {
    public static void execute() {

        if (!BankBranchRepository.getInstance().isExisting(1L)) {

            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            BankBranch bankBranch1 = context.getBean("branch1", BankBranch.class);

            BankBranchRepository.getInstance().save(bankBranch1);
        }
    }
}
