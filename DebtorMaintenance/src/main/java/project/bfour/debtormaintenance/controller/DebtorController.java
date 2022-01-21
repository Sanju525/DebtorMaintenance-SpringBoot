package project.bfour.debtormaintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import project.bfour.debtormaintenance.dao.DebtorDao;
import project.bfour.debtormaintenance.model.BankAccount;
import project.bfour.debtormaintenance.model.DebtorForm;
import project.bfour.debtormaintenance.model.Transaction;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class DebtorController {

    @Autowired
    DebtorDao debtorDao;

    @RequestMapping("/debtor/home")
    public String debtorHome(HttpSession session) {
        String who = (String) session.getAttribute("who");
        if (who == null) {
            return "redirect:/"; // login for debtor
        }
        if (who.equals("adm")) { // admin cannot access debtor pages
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return "debtorHome";
    }
//    :: Session completed

    @RequestMapping("/debtor/form")
    public String getDebtorForm(HttpSession session) {
        String who = (String) session.getAttribute("who");
        if (who == null) {
            return "redirect:/"; // debtor login
        }
        if (who.equals("adm")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return "debtorForm";
    }
//    :: Session completed

//    Session is ignored for POST
    @RequestMapping(value = "/debtor/form", method = RequestMethod.POST)
    public String postDebtorForm(Transaction transaction, DebtorForm debtorForm, BankAccount bankAccount) {
        debtorDao.insertForm(debtorForm, bankAccount, transaction);
        return "redirect:/debtor/home";
    }

    @RequestMapping("/debtor/transaction")
    public ModelAndView getTransactionAndConfirm(DebtorForm debtorForm, BankAccount bankAccount, HttpSession session) {
        String who = (String) session.getAttribute("who");
        ModelAndView modelAndView = new ModelAndView();
        if (who == null) {
            return new ModelAndView("redirect:/"); // debtor login
        }
        if (who.equals("adm")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        modelAndView.addObject("debtorForm", debtorForm);
        modelAndView.addObject("bankAccount", bankAccount);
        modelAndView.addObject("transaction", debtorDao.getNewTxn());
        modelAndView.setViewName("transaction");
        return modelAndView;
    }
//    :: Session completed

    @RequestMapping("/debtor/updates")
    public ModelAndView getForms(HttpSession session) { // Model attribute LIST
        String who = session.getAttribute("who").toString();
        if (who == null) {
            return new ModelAndView("redirect:/");
        }
        if (who.equals("adm")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("debtorUpdates");
        modelAndView.addObject("debtorList", debtorDao.getDebtorDetails(session.getAttribute("uid").toString()));
        return modelAndView;
    }
//    :: Session completed

    @RequestMapping("debtor/form/details/{txnId}")
    public ModelAndView getFormDetails(@PathVariable String txnId, HttpSession session) {
        String who = (String) session.getAttribute("who");
        if (who == null) {
            return new ModelAndView("redirect:/");
        }
        if (who.equals("adm")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("debtorForm", debtorDao.getDebtorDetailsByTxnId(txnId));
        modelAndView.setViewName("viewDebtorDetails");
        return modelAndView;
    }
//    :: Session completed

    @RequestMapping("/debtor/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("who")!= null) {
            session.removeAttribute("username");
            session.removeAttribute("uid");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            if (session.getAttribute("who").equals("deb")) {
                session.removeAttribute("who");
                session.invalidate();
                return "redirect:/";
            } else {
                session.removeAttribute("who");
                session.invalidate();
                return  "redirect:/admin/login"; // for admin
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
//    :: Session completed

}
