package com.optimagrowth.licensingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimagrowth.licensingservice.model.License;
import com.optimagrowth.licensingservice.service.LicenseService;


/*
 * @RestController
 * 스프링 컨테이너에 해당 자바 클래스가 REST 기반 서비스에 사용됨을 지정하는 클래스용 자바 어노테이션
 * JSON 또는 XML로 서비스에 전달된 데이터 직렬화를 자동으로 처리
 * 
 * @RequestMapping
 * 컨트롤러가 노출하는 모든 엔드포인트의 최상위 URL을 설정 가능
 */
@RestController
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenseController {


    @Autowired
    private LicenseService licenseService;

    /*
     * ResponseEntity
     * 상태코드, 헤더, 바디를 포함한 모든 HTTP 응답을 나타냄
     */
    @GetMapping(value="/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        License license = licenseService.getLicense(licenseId, organizationId);
        return ResponseEntity.ok(license);
    }
    
    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable("organizationId") String organizationId, @RequestBody License license) {
        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId));
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable("organizationId") String organizationId, @RequestBody License license) {
        return ResponseEntity.ok(licenseService.createLicense(license, organizationId));
    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }
}
