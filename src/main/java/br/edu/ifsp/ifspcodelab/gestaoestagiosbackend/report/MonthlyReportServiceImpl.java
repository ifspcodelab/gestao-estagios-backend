package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class MonthlyReportServiceImpl implements MonthlyReportService {
    private final MonthlyReportRepository monthlyReportRepository;

    private InternshipService internshipService;
    private UploadService uploadService;

    public MonthlyReportServiceImpl(MonthlyReportRepository monthlyReportRepository) {
        this.monthlyReportRepository = monthlyReportRepository;
    }

    @Autowired
    public void setInternshipService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Override
    public MonthlyReport create(LocalDate month, ActivityPlan activityPlan, Internship internship) {
        return monthlyReportRepository.save(new MonthlyReport(month, activityPlan, internship));
    }

    @Override
    public MonthlyReport update(MonthlyReport monthlyReport) {
        return monthlyReportRepository.save(monthlyReport);
    }

    @Override
    public MonthlyReport sendAttachment(UUID internshipId, UUID monthlyReportId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);
        MonthlyReport report = findById(monthlyReportId);

        uploadService.monthlyReportFileValidation(file);
        String reportAttachmentUrl = uploadService.uploadFile(file, getFileName(internship));

        report.setAttachmentUrl(reportAttachmentUrl);
        return monthlyReportRepository.save(report);
    }

    @Override
    public MonthlyReport findById(UUID id) {
        return monthlyReportRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.MONTHLY_REPORT, id));
    }

    private String getFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
            "/" +
            internship.getId().toString() +
            "/" +
            System.currentTimeMillis() +
            "-anexo";
    }
}
