package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Service
@AllArgsConstructor
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;

    @Override
    public Internship create(AdvisorRequest advisorRequest) {
        Internship internship = new Internship(advisorRequest);
        return internshipRepository.save(internship);
    }

    @Override
    public Internship findById(UUID id) {
        return internshipRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.INTERNSHIP, id));
    }

    @Override
    public List<Internship> findAllByAdvisorRequestStudentId(UUID studentId) {
        return internshipRepository.findAllByAdvisorRequestStudentId(studentId);
    }

    @Override
    public List<Internship> findAllByAdvisorRequestAdvisorId(UUID advisorId) {
        return internshipRepository.findAllByAdvisorRequestAdvisorId(advisorId);
    }

    @Override
    public Internship update(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public Internship updateStatus(UUID internshipId) {
        Internship internship = this.internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.INTERNSHIP, internshipId));

        internship.setStatus(InternshipStatus.FINISHED);

        return this.internshipRepository.save(internship);
    }

    @Override
    public byte[] generateFinalDocumentation(UUID internshipId) {
        Internship internship = findById(internshipId);

//        if(!internship.getStatus().equals(InternshipStatus.REALIZATION_TERM_ACCEPTED)){
//            throw new
//        }
        List<String> urls = new ArrayList<>();

//        for(ActivityPlan plan : internship.getActivityPlans()) {
//            if(plan.getStatus().equals(RequestStatus.ACCEPTED)){
//                urls.add(plan.getActivityPlanUrl());
//
//                internship.getMonthlyReports().stream()
//                        .filter(m -> m.getStatus().equals(ReportStatus.FINAL_ACCEPTED) &&
//                                m.getActivityPlan().getId() == plan.getId())
//                        .sorted(Comparator.comparing(MonthlyReport::getMonth))
//                        .forEach(m -> {
//                            urls.add(m.getFinalMonthlyReportUrl());
//                            if(m.getAttachmentUrl() != null){
//                                urls.add(m.getAttachmentUrl());
//                            }
//                        });
//            }
//        }


//        internship.getActivityPlans().stream()
//                .filter(a -> a.getStatus().equals(RequestStatus.ACCEPTED))
//                .sorted(Comparator.comparing(ActivityPlan::getCreatedAt))
//                .forEach(a -> urls.add(a.getActivityPlanUrl()));

        internship.getActivityPlans().stream()
                .filter(a -> a.getStatus().equals(RequestStatus.ACCEPTED))
                .sorted(Comparator.comparing(ActivityPlan::getCreatedAt))
                .forEach(a -> {
                    urls.add(a.getActivityPlanUrl());

                    internship.getMonthlyReports().stream()
                        .filter(m -> m.getStatus().equals(ReportStatus.FINAL_ACCEPTED) &&
                             m.getActivityPlan().getId() == a.getId())
                        .sorted(Comparator.comparing(MonthlyReport::getMonth))
                        .forEach(m -> {
                            urls.add(m.getFinalMonthlyReportUrl());
                            if(m.getAttachmentUrl() != null){
                                urls.add(m.getAttachmentUrl());
                            }
                        });
                });

//        internship.getMonthlyReports().stream()
//                .filter(m -> m.getStatus().equals(ReportStatus.FINAL_ACCEPTED))
//                .sorted(Comparator.comparing(MonthlyReport::getMonth))
//                .forEach(m -> {
//                    urls.add(m.getFinalMonthlyReportUrl());
//                    if(m.getAttachmentUrl() != null){
//                        urls.add(m.getAttachmentUrl());
//                    }
//                });

        internship.getRealizationTerms().stream()
                .filter(a -> a.getStatus().equals(RequestStatus.ACCEPTED))
                .forEach(a -> urls.add(a.getRealizationTermUrl()));

        List<InputStream> inputStreams = new ArrayList<>();
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            for(String url : urls) {
                inputStreams.add(new URL(url).openStream());
            }
            PDFMergerUtility pdf = new PDFMergerUtility();
            pdf.setDestinationStream(output);
            pdf.addSources(inputStreams);
            pdf.mergeDocuments(null);
        } catch (IOException ex) {
            //criar exceção personalizada
        }

        return output.toByteArray();
    }
}
