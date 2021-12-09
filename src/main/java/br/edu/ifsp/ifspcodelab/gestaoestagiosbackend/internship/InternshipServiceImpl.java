package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.InternshipWithoutRealizationTermAcceptedException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.MergeFilesException;
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
        Internship internship = findById(internshipId);

        internship.setStatus(InternshipStatus.FINISHED);

        return this.internshipRepository.save(internship);
    }

    @Override
    public FinalDocumentationDto generateFinalDocumentation(UUID internshipId) {
        Internship internship = findById(internshipId);

        if(!internship.getStatus().equals(InternshipStatus.REALIZATION_TERM_ACCEPTED)){
            throw new InternshipWithoutRealizationTermAcceptedException(internship.getId());
        }
        List<String> urls = new ArrayList<>();

        internship.getActivityPlans().stream()
            .filter(a -> a.getStatus().equals(RequestStatus.ACCEPTED))
            .sorted(Comparator.comparing(ActivityPlan::getCreatedAt))
            .forEach(a -> {
                urls.add(a.getActivityPlanUrl());
                urls.addAll(getMonthlyReportsAndAttachments(internship, a));
            });

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
            throw new MergeFilesException(ex.getMessage());
        }

        return new FinalDocumentationDto(output.toByteArray(), internship);
    }

    private List<String> getMonthlyReportsAndAttachments(Internship internship, ActivityPlan activityPlan) {
        List<String> urls = new ArrayList<>();

        internship.getMonthlyReports().stream()
            .filter(m -> m.getStatus().equals(ReportStatus.FINAL_ACCEPTED) &&
                m.getActivityPlan().getId() == activityPlan.getId())
            .sorted(Comparator.comparing(MonthlyReport::getMonth))
            .forEach(m -> {
                urls.add(m.getFinalMonthlyReportUrl());
                if(m.getAttachmentUrl() != null){
                    urls.add(m.getAttachmentUrl());
                }
            });

        return urls;
    }
}
