package bz.faycal.smallbank;

import bz.faycal.smallbank.entities.Account;
import bz.faycal.smallbank.entities.CurrentAccount;
import bz.faycal.smallbank.entities.Deposit;
import bz.faycal.smallbank.entities.Withdrawal;
import bz.faycal.smallbank.repository.AccountRepository;
import bz.faycal.smallbank.repository.OperationRepository;
import bz.faycal.smallbank.service.BankServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Test (black box) : bank transactions")
@Tag("Unit")
class OperationTransTest {

    @InjectMocks
    BankServiceImpl bankService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    OperationRepository operationRepository;


    @Nested
    @DisplayName("Deposit operations")
    class DepositOperations {
        private Account ac;

        @BeforeEach
        void init() {
            ac = new CurrentAccount("A1", new Date(), 1000, null, 10);
        }

        @Test
        @DisplayName("Test of Deposit operation")
        void testDepositOperation() {
            double amount = 1000;
            when(accountRepository.findById("A1")).thenReturn(Optional.of(ac));
            when(operationRepository.save(any(Deposit.class))).thenReturn(any());
            when(accountRepository.save(ac)).thenReturn(ac);
            double oldBalance = ac.getBalance();
            bankService.deposit("A1", amount);
            assertThat(ac.getBalance()).isEqualTo(amount + oldBalance);
            /*verify(accountRepository).findById("A1");
            verify(operationRepository).save(any(Deposit.class));
            verify(accountRepository).save(ac);*/
        }


        @ParameterizedTest
        @ValueSource(doubles = {-1000, 0})
        @DisplayName("Test Deposit operation with wrong values")
        void testWrongDepositOperation(double amount) {
            assertThrows(Exception.class, () -> bankService.deposit("A1", amount));
        }

    }

    @Nested
    @DisplayName("Withdraw operations")
    class WithDrawOperations{
        private Account ac;

        @BeforeEach
        void init() {
            ac = new CurrentAccount();
            ac.setBalance(1000);
        }

        @Test
        @DisplayName("Test Withdraw operation with smaller amount then the balance account")
        void testWithdrawOperationSmallAMount(){
            double amount=100;
            when(accountRepository.findById(any(String.class))).thenReturn(Optional.of(ac));
            when(operationRepository.save(any(Withdrawal.class))).thenReturn(any());
            when(accountRepository.save(ac)).thenReturn(ac);
            double oldAMount=ac.getBalance();
            bankService.withdraw("A1",amount);
            assertThat(ac.getBalance()).isEqualTo(oldAMount-amount);
        }

        @Test
        @DisplayName("Test Withdraw operation with bigger amount then the balance account")
        void testWithdrawOperationBigAMount(){
            double amount=1000000;
            when(accountRepository.findById(any(String.class))).thenReturn(Optional.of(ac));
            assertThrows(Exception.class,()->  bankService.withdraw("A1",amount));
        }

        @ParameterizedTest
        @ValueSource(doubles = {0,-100})
        void testWrongWithDrawOperations(double amount){
            assertThrows(Exception.class,()->  bankService.withdraw("A1",amount));
        }
    }

}
