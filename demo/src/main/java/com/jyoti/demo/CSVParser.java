package com.jyoti.demo;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CSVParser {
    public static final String CSV_FILE = "/Users/jyotiranjan.jena/Downloads/anonymized_mailboxes-20200619-12-23-28.csv";
    public static AtomicInteger PECount = new AtomicInteger();
    public static AtomicInteger SECount = new AtomicInteger();
    public static AtomicInteger BEPCount = new AtomicInteger();
    public static AtomicInteger BECount = new AtomicInteger();
    public static AtomicInteger EWSCount = new AtomicInteger();

    public static void main(String[] args) {
        //SEList.addAll(PEList);
        readAllDataAtOnce(CSV_FILE);
    }

    public static void readAllDataAtOnce(String file) {
        try {
            // Create an object of file reader 
            // class with CSV file as a parameter. 
            FileReader filereader = new FileReader(file);

            Map<String, String> mapping = new HashMap<String, String>();
            mapping.put("email", "email");
            mapping.put("domain", "domain");
            mapping.put("cos", "cos");
            mapping.put("zimbraFeatureMAPIConnectorEnabled", "zimbraFeatureMAPIConnectorEnabled");
            mapping.put("zimbraFeatureMobileSyncEnabled", "zimbraFeatureMobileSyncEnabled");
            mapping.put("zimbraArchiveEnabled", "zimbraArchiveEnabled");
            mapping.put("zimbraFeatureConversationsEnabled", "zimbraFeatureConversationsEnabled");
            mapping.put("zimbraFeatureTaggingEnabled", "zimbraFeatureTaggingEnabled");
            mapping.put("zimbraFeatureAdvancedSearchEnabled", "zimbraFeatureAdvancedSearchEnabled");
            mapping.put("zimbraAttachmentsIndexingEnabled", "zimbraAttachmentsIndexingEnabled");
            mapping.put("zimbraFeatureViewInHtmlEnabled", "zimbraFeatureViewInHtmlEnabled");
            mapping.put("zimbraFeatureGroupCalendarEnabled", "zimbraFeatureGroupCalendarEnabled");
            mapping.put("zimbraFreebusyExchangeURL", "zimbraFreebusyExchangeURL");
            mapping.put("zimbraFeatureSharingEnabled", "zimbraFeatureSharingEnabled");
            mapping.put("zimbraFeatureTasksEnabled", "zimbraFeatureTasksEnabled");
            mapping.put("zimbraFeatureBriefcasesEnabled", "zimbraFeatureBriefcasesEnabled");
            mapping.put("zimbraFeatureSMIMEEnabled", "zimbraFeatureSMIMEEnabled");
            mapping.put("zimbraFeatureVoiceEnabled", "zimbraFeatureVoiceEnabled");
            mapping.put("zimbraFeatureManageZimlets", "zimbraFeatureManageZimlets");
            mapping.put("zimbraFeatureCalendarEnabled", "zimbraFeatureCalendarEnabled");
            mapping.put("zimbraFeatureEwsEnabled", "zimbraFeatureEwsEnabled");
            HeaderColumnNameTranslateMappingStrategy<Usage> strategy = new HeaderColumnNameTranslateMappingStrategy<Usage>();
            strategy.setType(Usage.class);
            strategy.setColumnMapping(mapping);
            // create csvReader object and skip first Line 
            CSVReader csvReader = new CSVReader(filereader);
            CsvToBean<Usage> csvToBean = new CsvToBean<Usage>(); 
            
            // call the parse method of CsvToBean 
            // pass strategy, csvReader to parse method 
            List<Usage> list = csvToBean.parse(strategy, csvReader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/jyotiranjan.jena/accts_generated_9481.csv"));
            Set<String> domains = new HashSet<String>();
            //Random rand = new Random();
            for (Usage usage : list) {
                //long drand = (long)(rand.nextDouble()*100L);
                String email = usage.getEmail();
                domains.add(usage.getDomain());
                writer.write("ca " + email + " test123" +
                        " zimbraFeatureMAPIConnectorEnabled " + usage.isZimbraFeatureMAPIConnectorEnabled() + " zimbraFeatureMobileSyncEnabled " + usage.isZimbraFeatureMobileSyncEnabled() +
                        " zimbraArchiveEnabled " + usage.isZimbraArchiveEnabled() + " zimbraFeatureConversationsEnabled " + usage.isZimbraFeatureConversationsEnabled() + 
                        " zimbraFeatureTaggingEnabled " + usage.isZimbraFeatureTaggingEnabled() + " zimbraFeatureAdvancedSearchEnabled " + usage.isZimbraFeatureAdvancedSearchEnabled() +
                        " zimbraAttachmentsIndexingEnabled " + usage.isZimbraAttachmentsIndexingEnabled() + " zimbraFeatureViewInHtmlEnabled " + usage.isZimbraFeatureViewInHtmlEnabled() +
                        " zimbraFeatureGroupCalendarEnabled " + usage.isZimbraFeatureGroupCalendarEnabled() + " zimbraFreebusyExchangeURL " + usage.isZimbraFreebusyExchangeURL() +
                        " zimbraFeatureSharingEnabled " + usage.isZimbraFeatureSharingEnabled() + " zimbraFeatureTasksEnabled " + usage.isZimbraFeatureTasksEnabled() + " zimbraFeatureBriefcasesEnabled " + usage.isZimbraFeatureBriefcasesEnabled() +
                        " zimbraFeatureSMIMEEnabled " + usage.isZimbraFeatureSMIMEEnabled() + " zimbraFeatureVoiceEnabled " + usage.isZimbraFeatureVoiceEnabled() +
                        " zimbraFeatureManageZimlets " + usage.isZimbraFeatureManageZimlets() + " zimbraFeatureCalendarEnabled " + usage.isZimbraFeatureCalendarEnabled() +
                        " zimbraFeatureEwsEnabled " + usage.isZimbraFeatureEwsEnabled() + "\n");
            }
            writer.close();
            writer = new BufferedWriter(new FileWriter("/Users/jyotiranjan.jena/domains.csv"));
            for (String domain : domains) {
                writer.write("cd " + domain + "\n");
            }
            writer.close();
            /*for (Usage usage : list) {
                if (isSEFeatureEnabled(usage)) {
                    if (isPEFeatureEnabled(usage)) {
                        PECount.getAndIncrement();
                    } else {
                        SECount.getAndIncrement();
                    }
                    if (isEwsEnabled(usage)) {
                        EWSCount.getAndIncrement();
                    }
                } else if (isPEFeatureEnabled(usage)) {
                    PECount.getAndIncrement();
                    if (isEwsEnabled(usage)) {
                        EWSCount.getAndIncrement();
                    }
                } else if(isBEPFeatureEnabled(usage)) {
                    BEPCount.getAndIncrement();
                    if (isEwsEnabled(usage)) {
                        EWSCount.getAndIncrement();
                    }
                } else if(isAccountUnderBE(usage)) {
                    BECount.getAndIncrement();
                    if (isEwsEnabled(usage)) {
                        EWSCount.getAndIncrement();
                    }
                }
            }
            System.out.println("Account's With SE(only) Feature Enabled  : " + SECount.get());
            System.out.println("Account's With PE(SE + PE) Feature Enabled  : " + PECount.get());
            System.out.println("Account's With BEP Feature Enabled : " + BEPCount.get());
            System.out.println("Account's With BE Feature Enabled  : " + BECount.get());
            System.out.println("Account's With EWS Feature Enabled : " + EWSCount.get());
            System.out.println("Total Account Count : " + (SECount.get() + PECount.get() + BEPCount.get() + BECount.get()));*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSEFeatureEnabled(Usage usage) {
        if (usage.isZimbraFeatureMAPIConnectorEnabled() || usage.isZimbraFeatureMobileSyncEnabled() || usage.isZimbraArchiveEnabled() ||
                usage.isZimbraFeatureConversationsEnabled() || usage.isZimbraFeatureTaggingEnabled() || usage.isZimbraAttachmentsIndexingEnabled() ||
                usage.isZimbraFeatureViewInHtmlEnabled() || usage.isZimbraFeatureGroupCalendarEnabled() || usage.isZimbraFeatureSharingEnabled() ||
                usage.isZimbraFeatureTasksEnabled() || usage.isZimbraFeatureBriefcasesEnabled() || usage.isZimbraFeatureSMIMEEnabled() ||
                usage.isZimbraFeatureVoiceEnabled()) {
            return true;
        }
        return false;
    }

    public static boolean isPEFeatureEnabled(Usage usage) {
        if (usage.isZimbraFeatureMAPIConnectorEnabled() || usage.isZimbraFeatureMobileSyncEnabled() || usage.isZimbraArchiveEnabled()) {
            return true;
        }
        return false;
    }

    public static boolean isBEPFeatureEnabled(Usage usage) {
        return (usage.isZimbraFeatureManageZimlets() || usage.isZimbraFeatureCalendarEnabled()) && (!isSEFeatureEnabled(usage) || !isAccountUnderBE(usage) || !isBEPFeatureEnabled(usage));
    }

    public static boolean isAccountUnderBE(Usage usage) {
        return (!isSEFeatureEnabled(usage) || !isPEFeatureEnabled(usage) || !isBEPFeatureEnabled(usage));
    }

    public static boolean isEwsEnabled(Usage usage) {
        return usage.isZimbraFeatureEwsEnabled();
    }
}
