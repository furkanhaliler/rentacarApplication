package com.turkcell.rentacar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.adapters.posAdapters.IsBankPosAdapter;
import com.turkcell.rentacar.business.dtos.lists.PaymentListDto;
import com.turkcell.rentacar.business.outServices.HalkBankPosManager;
import com.turkcell.rentacar.business.outServices.IsBankPosManager;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.core.business.abstracts.PosService;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class PaymentManager implements PaymentService {

	
	
	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private PosService posService;
	private RentService rentService;
	
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, PosService posService, RentService rentService) {

		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.posService = posService;
		this.rentService = rentService;		
	}

	
	
	@Override
	public Result add(CreatePaymentRequest createPaymentRequest, Rent rent) throws BusinessException {
		
		PosService posService2 = new IsBankPosAdapter();
		
		if(posService2.pay(createPaymentRequest)) {
		
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		payment.setRent(rent);
		
		this.paymentDao.save(payment);
		
		return new SuccessResult("Ödeme yapıldı.");
		}
		
		throw new BusinessException("Ödeme yapılırken hata oluştu.");
	}
	//üst kısımda ödeme başarılı olursa runpayment successorü çalıştıracak. transactionu burada yapacağız. 
	//transactional yap
	public void runPaymentSuccessor() {
		
		//rent ekle. 
		//additional servis ekle.
		//invoice
		//
	}

	@Override
	public DataResult<List<PaymentListDto>> getAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
