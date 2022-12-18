package ru.job4j.url.shortcut.service.website;

import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.domain.Website;
import ru.job4j.url.shortcut.repository.WebsiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WebsiteServices implements WebServices {
    private final WebsiteRepository websiteRepository;

    public WebsiteServices(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    public List<Website> findAll() {
        return websiteRepository.findAll();
    }

    public Optional<Website> findById(int id) {
        return websiteRepository.findById(id);
    }

    public Website save(Website website) {
        websiteRepository.save(website);
        return website;
    }

    public void delete(Website website) {
        websiteRepository.delete(website);
    }

    public Optional<Website> findBySite(String site) {
        return websiteRepository.findBySite(site);
    }

}
