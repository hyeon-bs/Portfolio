// CBinary.cpp: 구현 파일
//

#include "stdafx.h"
#include "Exam.h"
#include "CBinary.h"
#include "afxdialogex.h"


// CBinary 대화 상자

IMPLEMENT_DYNAMIC(CBinary, CDialog)

CBinary::CBinary(CWnd* pParent /*=nullptr*/)
	: CDialog(IDD_BINARY, pParent)
	, m_input(0)
{

}

CBinary::~CBinary()
{
}

void CBinary::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_INPUT, m_input);
}


BEGIN_MESSAGE_MAP(CBinary, CDialog)
END_MESSAGE_MAP()


// CBinary 메시지 처리기
