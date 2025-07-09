package com.techmatrix18;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Check a balance of Ethereum-wallet address
 * Comprobar el saldo de una dirección de cartera Ethereum
 * Проверка баланса Ethereum-адреса кошелька
 *
 * @author Alexander Kuziv - makklays@gmail.com
 * @since 09-07-2025
 * @version 0.0.1
 */

public class EthBalanceChecker {

    public static void main(String[] args) throws Exception {
        // Подключаемся к публичному Ethereum-узлу (например, Infura)
        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"));

        // Адрес кошелька, который хотим проверить
        String walletAddress = "0xYourWalletAddressHere";

        // Получаем баланс в Wei (1 ETH = 10^18 Wei)
        EthGetBalance ethGetBalance = web3.ethGetBalance(walletAddress, org.web3j.protocol.core.DefaultBlockParameterName.LATEST).send();

        BigInteger wei = ethGetBalance.getBalance();

        // Конвертируем в ETH
        BigDecimal etherBalance = Convert.fromWei(new BigDecimal(wei), Convert.Unit.ETHER);

        System.out.println("Баланс кошелька " + walletAddress + " : " + etherBalance + " ETH");

        // Пример проверки — есть ли баланс больше 0
        if (etherBalance.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Оплата получена!");
        } else {
            System.out.println("Оплата не поступала.");
        }
    }
}

