package project.bfour.debtormaintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import project.bfour.debtormaintenance.dao.AdminDao;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    AdminDao adminDao;

    @RequestMapping("/admin/home")
    public String adminHome(HttpSession session) {
        String who = (String) session.getAttribute("who");
        if (who == null) {
            return "redirect:/admin/login";
        }
        if (who.equals("deb")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        session.setAttribute("pending", adminDao.getPendingFormCount());
        return "adminHome";
    }
//    :: Session completed

    @RequestMapping("/admin/debtor/details") // require admin auth
    public ModelAndView getAllDebtorDetails(HttpSession session) {
        String who = (String) session.getAttribute("who");
        if (who == null) {
            return new ModelAndView("redirect:/admin/login");
        }
        if (who.equals("deb")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("debtorList", adminDao.getDebtorDetails());
        modelAndView.setViewName("debtorDetailsList");
        return modelAndView;
    }
//    :: Session completed

    @RequestMapping("/admin/debtor/form/details/{txnId}")
    public ModelAndView getFormDetails(@PathVariable String txnId, HttpSession session) {
        String who = (String) session.getAttribute("who");
        if (who == null) {
            return new ModelAndView("redirect:/admin/login");
        }
        if (who.equals("deb")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("debtorForm", adminDao.getDebtorDetailsByTxnId(txnId));
        modelAndView.setViewName("viewDebtorDetails");
        return modelAndView;
    }
//    :: Session completed

    @RequestMapping("/admin/debtor/authorize/{txnId}")
    public String authorizeDebtor(HttpSession session, @PathVariable String txnId) {
        String who = (String) session.getAttribute("who");
        if (who == null) {
            return "redirect:/admin/login";
        }
        if (who.equals("deb")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if (adminDao.authorize(txnId) == 1) {
            session.setAttribute("auth", "success");
            return "redirect:/admin/debtor/details";
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
//    :: Session completed

    @RequestMapping("/admin/debtor/reject-form/{txnId}")
    public ModelAndView rejectFormDebtor(HttpSession session, @PathVariable String txnId) {
        String who = session.getAttribute("who").toString();
        if (who == null) {
            return new ModelAndView("redirect:/admin/login");
        }
        if (who.equals("deb")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("txnId", txnId);
        modelAndView.setViewName("rejectForm");
        return modelAndView;
    }
//    :: Session completed

//    Session is ignored for POST
    @RequestMapping(value = "/admin/debtor/reject", method = RequestMethod.POST)
    public String rejectDebtor(HttpSession session, @RequestParam String txnId, @RequestParam String rejectReason) {
        if (adminDao.reject(txnId, rejectReason) == 1) {
            session.setAttribute("reject", "success");
            return "redirect:/admin/debtor/details";
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/admin/logout")
    public String adminLogout(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("who")!= null) {
            session.removeAttribute("username");
            session.removeAttribute("uid");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            if (session.getAttribute("who").equals("adm")) {
                session.removeAttribute("who");
                session.invalidate();
                return "redirect:/admin/login";
            } else {
                session.removeAttribute("who");
                session.invalidate();
                return  "redirect:/"; // for debtor
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
//    :: Session completed

}
