package com.techmatrix18;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Uint;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * Check a balance of USDT ERC20 contract address
 * Comprobar el saldo de una dirección de cartera USDT ERC20
 * Проверка баланса USDT ERC20 контракт адреса кошелька
 *
 * @author Alexander Kuziv - makklays@gmail.com
 * @since 09-07-2025
 * @version 0.0.1
 */

public class UsdtBalanceChecker {

    public static void main(String[] args) throws Exception {
        String rpcUrl = "https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID";
        Web3j web3 = Web3j.build(new HttpService(rpcUrl));

        String contractAddress = "0xdAC17F958D2ee523a2206206994597C13D831ec7"; // USDT ERC20 контракт
        String walletAddress = "0xYourWalletAddress";

        Function function = new Function(
                "balanceOf",
                Collections.singletonList(new Address(walletAddress)),
                Collections.singletonList(new TypeReference<Uint>() {}));

        String encodedFunction = org.web3j.abi.FunctionEncoder.encode(function);

        EthCall response = web3.ethCall(
                        Transaction.createEthCallTransaction(walletAddress, contractAddress, encodedFunction),
                        org.web3j.protocol.core.DefaultBlockParameterName.LATEST)
                .send();

        String value = response.getValue();
        BigInteger balance = new BigInteger(value.substring(2), 16);

        System.out.println("Баланс USDT: " + balance);
    }
}

