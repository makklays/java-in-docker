package com.techmatrix18;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;
import java.io.File;
import java.math.BigDecimal;

public class Web3jMyCriptoWallet {

    public static void main(String[] args) throws Exception {
        // Путь для сохранения кошелька
        String walletDir = "./wallets";
        new File(walletDir).mkdirs();

        String password = "myStrongPassword";

        // Создать новый кошелек
        String walletFileName = WalletUtils.generateFullNewWalletFile(password, new File(walletDir));
        System.out.println("New wallet file: " + walletFileName);

        // Загрузить кошелек из файла
        Credentials credentials = WalletUtils.loadCredentials(password, walletDir + "/" + walletFileName);
        System.out.println("Wallet address: " + credentials.getAddress());

        // Подключение к Ethereum ноде (например, Infura)
        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR-PROJECT-ID"));

        // Получить баланс кошелька
        EthGetBalance ethGetBalance = web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigDecimal balanceInEther = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);

        System.out.println("Balance: " + balanceInEther + " ETH");
    }
}

