package com.example.miniProject.controller;

import com.example.miniProject.config.Config;
import com.example.miniProject.model.Accounts;
import com.example.miniProject.model.Payment;
import com.example.miniProject.model.Wallets;
import com.example.miniProject.repository.AccountRepository;
import com.example.miniProject.repository.WalletRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/createPayment")
    public ResponseEntity<?> createPayment(HttpServletRequest request, @RequestParam("amount") String amountStr, @RequestParam("id") int accountId) throws UnsupportedEncodingException {

        Accounts account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "billpayment";
        long amount = Long.parseLong(amountStr)*100;
        String bankCode = "NCB";

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(request);
        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");

        vnp_Params.put("vnp_ReturnUrl", "http://127.0.0.1:5500/wallet.html");
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);


        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        Payment payment = new Payment();
        payment.setStatus("OK");
        payment.setMessage("Successfully");
        payment.setURL(paymentUrl);


            double amountPaid = Double.parseDouble(amountStr); // Số tiền được thanh toán
            Wallets wallet = walletRepository.findByUserid(accountId);
            if (wallet != null) {
                double currentBalance = wallet.getBalance();
                double newBalance = currentBalance + amountPaid / 1000;
                wallet.setBalance(newBalance);
                walletRepository.save(wallet);
            }
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

}
