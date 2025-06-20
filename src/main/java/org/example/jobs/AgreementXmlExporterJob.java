package org.example.jobs;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.api.command.TravelGetAllAgreementUuidsCoreCommand;
import org.example.core.api.command.TravelGetAllAgreementUuidsCoreResult;
import org.example.core.services.TravelGetAllAgreementUuidsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    @Value("${agreement.xml.exporter.job.thread.count}")
    private Integer threadCount;

    private final TravelGetAllAgreementUuidsService allAgreementUuidsService;
    private final AgreementXmlExporter agreementXmlExporter;

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if (jobEnabled) {
            executeJob();
        }
    }

    private void executeJob() {
        logger.info("AgreementXmlExporterJob started");
        List<String> allAgreementUuids = getAllAgreementUuids();
        exportAgreements(allAgreementUuids);
        logger.info("AgreementXmlExporterJob finished");
    }

    private List<String> getAllAgreementUuids() {
        TravelGetAllAgreementUuidsCoreResult result = allAgreementUuidsService.getAgreement(
                new TravelGetAllAgreementUuidsCoreCommand()
        );
        return result.getAgreementUuids();
    }

    private void exportAgreements(List<String> agreementUuids) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Collection<Future<?>> futures = new LinkedList<>();
        agreementUuids.forEach(uuid -> futures.add(executor.submit(() -> agreementXmlExporter.exportAgreement(uuid))));
        waitUntilAllTasksWillBeExecuted(futures);
        executor.shutdownNow();
    }

    private static void waitUntilAllTasksWillBeExecuted(Collection<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                logger.info("AgreementXmlExporterJob exception", e);
            } catch (ExecutionException e) {
                logger.info("AgreementXmlExporterJob exception", e);
            }
        }
    }

}
